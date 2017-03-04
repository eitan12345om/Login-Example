package com.simlerentertainment.loginexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    public void onButtonClick(View view) {
        final AlertDialog alertDialog = new AlertDialog.Builder(PayForwardActivity.this).create();
        alertDialog.setTitle("Do you want to sign out?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), PhoneNumberActivity.class);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
}
