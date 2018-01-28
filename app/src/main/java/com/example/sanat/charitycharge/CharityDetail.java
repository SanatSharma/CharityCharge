package com.example.sanat.charitycharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class CharityDetail extends AppCompatActivity {

    TextView charityName;
    TextView charityDescription;
    TextView charityWebsite;
    ImageView charityImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_charity_detail);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");

        Globals.Charity charity = Globals.getInstance().getCharity(id);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(charity.name);

        charityName = (TextView) findViewById(R.id.charity_name);
        charityWebsite = (TextView) findViewById(R.id.website);
        charityDescription = (TextView) findViewById(R.id.description);
        charityImage = (ImageView) findViewById(R.id.charity_image);

        Glide.with(this).load(charity.image).into(charityImage);

        charityName.setText(charity.name);
        charityWebsite.setText(charity.website);
        charityDescription.setText(charity.description);

        Button button = (Button) findViewById(R.id.donate_button);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //find id
                final int new_id = id;

                Intent i = new Intent(getApplicationContext(), Donation.class);

                Bundle b = new Bundle();
                b.putInt("id", new_id);
                i.putExtras(b);

                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_charity_list, menu);
        return true;
    }

}
