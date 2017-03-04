package com.simlerentertainment.loginexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * This activity is the first step in signing up
 * Users will also be able to access the terms and conditions
 * from the activity.
 */
public class PhoneNumberActivity extends AppCompatActivity {

    private EditText mPhoneNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        mPhoneNumberView = (EditText) findViewById(R.id.phoneNumber);

        // Creates onClick Listener for Submit button
        Button mPhoneButton = (Button) findViewById(R.id.phone_button);
        mPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptProceed();
            }
        });
    }

    /**
     * Atempts to proceed to next activity. If it fails, there are
     * errors in the form.
     */
    private void attemptProceed() {
        mPhoneNumberView.setError(null);

        boolean cancel = false;
        View focusView = null;
        String phoneNumber = mPhoneNumberView.getText().toString();

        // Check if phone number valid
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberView.setError(getString(R.string.error_field_required));
            focusView = mPhoneNumberView;
            cancel = true;
        } else if (phoneNumber.length() < 10) {
            mPhoneNumberView.setError(getString(R.string.error_invalid_phone));
            focusView = mPhoneNumberView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Go to PayForward Activity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
