package com.example.posstrsoftware.posstrsoftware.model;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by Wasabi on 11/29/2016.
 */

public class SelectDateFragment extends DialogFragment {
    DatePickerDialog.OnDateSetListener ondateSet;
    public SelectDateFragment() {
    }
    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        ondateSet = ondate;
    }
    private int year ;
    private int month ;
    private int day;
    /*@Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), ondateSet, yy, mm, dd);
    }

}
