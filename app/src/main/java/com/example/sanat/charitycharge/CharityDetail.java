package com.example.sanat.charitycharge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CharityDetail extends AppCompatActivity {

    TextView charityName;
    TextView charityDescription;
    TextView charityWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        Globals.Charity charity = Globals.getInstance().getCharity(id);

        new ImageBackground((ImageView) findViewById(R.id.charityImage))
                .execute(charity.image);

        charityName = (TextView) findViewById(R.id.charity_name);
        charityWebsite = (TextView) findViewById(R.id.website);
        charityDescription = (TextView) findViewById(R.id.description);

        charityName.setText(charity.name);
        charityWebsite.setText(charity.website);
        charityDescription.setText(charity.description);



    }

}
