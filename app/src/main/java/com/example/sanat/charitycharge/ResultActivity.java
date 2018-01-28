package com.example.sanat.charitycharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        final String text = b.getString("text");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setContentView(R.layout.activity_charity_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Relevant Charities");

        List<String> items = Globals.getInstance().getRelevantCharities(text);

        ArrayList<String> icons = new ArrayList<String>();

        for (int index = 0; index < items.size(); index++) {
            icons.add(Globals.getInstance().getCharityIcon(items.get(index)));
        }

        listView = (ListView) findViewById(R.id.list);

        CharityLayout itemsAdapter = new CharityLayout(this, new ArrayList(items), new ArrayList(items), icons);

        Log.v("Test", itemsAdapter.getItem(0));

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) listView.getItemAtPosition(position);

                //find id
                int charityId = Globals.getInstance().getId(itemValue);

                Intent i = new Intent(getApplicationContext(), CharityDetail.class);

                Bundle b = new Bundle();
                b.putInt("id", charityId);
                i.putExtras(b);

                startActivity(i);

            }

        });
    }

}


