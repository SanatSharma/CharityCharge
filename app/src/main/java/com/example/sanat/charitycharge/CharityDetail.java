package com.example.sanat.charitycharge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
        int id = b.getInt("id");

        Globals.Charity charity = Globals.getInstance().getCharity(id);


        charityName = (TextView) findViewById(R.id.charity_name);
        charityWebsite = (TextView) findViewById(R.id.website);
        charityDescription = (TextView) findViewById(R.id.description);
        charityImage = (ImageView) findViewById(R.id.charity_image);

        Glide.with(this).load(charity.image).into(charityImage);

        //new ImageBackground(charityImage).execute(charity.image);

        charityName.setText(charity.name);
        charityWebsite.setText(charity.website);
        charityDescription.setText(charity.description);



    }

}
