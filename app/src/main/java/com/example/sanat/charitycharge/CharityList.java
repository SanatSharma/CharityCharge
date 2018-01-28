package com.example.sanat.charitycharge;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class CharityList extends AppCompatActivity {

    // This is the Adapter being used to display the list's data
    ListView listView;
    ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charity_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Featured Charities");

        ArrayList<String> items = Globals.getInstance().getNames();

        ArrayList<String> icons = new ArrayList<String>();

        for (int index = 0; index < items.size(); index++) {
            icons.add(Globals.getInstance().getCharityIcon(items.get(index)));
        }

        listView = (ListView) findViewById(R.id.list);

        CharityLayout itemsAdapter = new CharityLayout(this, items, items, icons);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_charaty_main, menu);
        return true;
    }

}