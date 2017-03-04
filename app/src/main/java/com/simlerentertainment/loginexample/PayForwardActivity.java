package com.simlerentertainment.loginexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class PayForwardActivity extends AppCompatActivity {

    private Button mID_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_forward);

        Intent intent = getIntent();
        String name = intent.getStringExtra("First Name");

        // Set ID
        mID_TextView = (Button) findViewById(R.id.myID);
        mID_TextView.setText(name);
    }

}
