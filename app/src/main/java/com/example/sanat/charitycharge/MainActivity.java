package com.example.sanat.charitycharge;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent i = new Intent(this, CharityList.class);


        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(i);
                        finish();
                    }
                }, 3000
        );
    }
}

