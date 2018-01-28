package com.example.sanat.charitycharge;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by david on 1/28/18.
 */

public class Donation extends AppCompatActivity implements View.OnClickListener{

    private int[] buttonIds = new int[] {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7};
    private Button[] buttons = new Button[7];
    TextView btn_unfocus;
    EditText textField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.donationTitle);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) findViewById(buttonIds[i]);
            buttons[i].setBackgroundColor(Color.rgb(207, 207, 207));
            buttons[i].setOnClickListener(this);
        }
        textField = findViewById(buttonIds[7]);
        textField.setOnClickListener(this);
        btn_unfocus = buttons[0];
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn0:
                setFocus(btn_unfocus, buttons[0]);
                break;
            case R.id.btn1:
                setFocus(btn_unfocus, buttons[1]);
                break;
            case R.id.btn2:
                setFocus(btn_unfocus, buttons[2]);
                break;
            case R.id.btn3:
                setFocus(btn_unfocus, buttons[3]);
                break;
            case R.id.btn4:
                setFocus(btn_unfocus, buttons[4]);
                break;
            case R.id.btn5:
                setFocus(btn_unfocus, buttons[5]);
                break;
            case R.id.btn6:
                setFocus(btn_unfocus, buttons[6]);
                break;
            case R.id.btn7:
                setFocus(btn_unfocus, textField);
        }
    }

    private void setFocus(TextView btn_unfocus, TextView btn_focus) {
        if (btn_focus == findViewById(R.id.btn7)) {
            btn_focus.setText("");
        }

        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(3, 106, 150));
        this.btn_unfocus = btn_focus;
    }
}
