package com.simlerentertainment.loginexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eitan on 3/3/2017.
 * <p>
 * This class creates a DatePickerDialog for user to pick a birthday
 *
 * @author Eitan Simler
 */

public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        return datePickerDialog;
    }
}
