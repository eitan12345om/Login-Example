package com.simlerentertainment.loginexample;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A login screen that offers signup via several forms.
 */
public class LoginActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // Regex for email validation
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    // UI references.
    private EditText mFirstNameView;
    private EditText mLastNameView;
    private EditText mBirthdayView;
    private EditText mZipCodeView;
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mFirstNameView = (EditText) findViewById(R.id.firstName);
        mLastNameView = (EditText) findViewById(R.id.lastName);
        mBirthdayView = (EditText) findViewById(R.id.birthdayText);
        mZipCodeView = (EditText) findViewById(R.id.zipcode);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // onClick Listener for submit
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Required for interface. Creates new instance of calendar
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        setDate(calendar);
    }

    /**
     * Sets date of calendar and sets field text
     */
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        ((EditText) findViewById(R.id.birthdayText)).setText(dateFormat.format(calendar.getTime()));
    }

    /**
     * Creates and shows instance of datePicker on button click
     */
    public void datePicker(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "date");
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mFirstNameView.setError(null);
        mLastNameView.setError(null);
        mBirthdayView.setError(null);
        mZipCodeView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String firstName = mFirstNameView.getText().toString();
        String lastName = mLastNameView.getText().toString();
        String birthday = mBirthdayView.getText().toString();
        String zipCode = mZipCodeView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        String[] fields = new String[]{firstName, lastName, birthday, zipCode, email, password};
        EditText[] views = new EditText[]{mFirstNameView, mLastNameView, mBirthdayView,
                mZipCodeView, mEmailView, mPasswordView};


        boolean cancel = false;
        View focusView = null;

        EditText result = checkAllEmpty(fields, views);

        if (result != null) {
            result.setError(getString(R.string.error_field_required));
            focusView = result;
            cancel = true;
        } else if (zipCode.length() != 5) {
            mZipCodeView.setError(getString(R.string.error_invalid_zipCode));
            focusView = mZipCodeView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (password.length() < 6) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Go to PayForward Activity
            Intent intent = new Intent(getApplicationContext(), PayForwardActivity.class);
            // Send the name to PayForward Activity
            intent.putExtra("First Name", firstName);
            startActivity(intent);
        }
    }

    /**
     * Checks every field to see if they are empty
     */
    private EditText checkAllEmpty(String[] fields, EditText[] views) {
        for (int i = 0; i < fields.length; i++) {
            if (TextUtils.isEmpty(fields[i])) {
                return views[i];
            }
        }
        return null;
    }

    /**
     * Checks if email is valid using regex
     */
    private boolean isEmailValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

