package com.example.sanat.charitycharge;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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

        /*LayoutInflater inflater = this.getLayoutInflater();

        View myView = inflater.inflate(R.layout.activity_charity_list, null);*/

        setContentView(R.layout.activity_charity_list);

        listView = (ListView) findViewById(R.id.list);

        ArrayList<String> items = Globals.getInstance().getNames();
        
        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        CharityLayout itemsAdapter = new CharityLayout(this, items, items);

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