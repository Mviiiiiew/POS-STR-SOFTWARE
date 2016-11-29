package com.example.posstrsoftware.posstrsoftware.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.posstrsoftware.posstrsoftware.R;



/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportDayFragment extends Fragment implements View.OnClickListener {


    EditText edit_date;

    public ReportDayFragment() {
        super();
    }

    public static ReportDayFragment newInstance() {
        ReportDayFragment fragment = new ReportDayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_day, container, false);
        initInstances(rootView);
        return rootView;
    }





    private void showDatePicker() {
        com.example.posstrsoftware.posstrsoftware.model.SelectDateFragment date = new com.example.posstrsoftware.posstrsoftware.model.SelectDateFragment();
        date.setCallBack(ondate);
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }
    DatePickerDialog.OnDateSetListener  ondate = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Toast.makeText(getActivity(),                    String.valueOf(year) + "-" + String.valueOf(month+1)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_SHORT).show();
            edit_date.setText(year+"/"+(month+1)+"/"+dayOfMonth);
        }
    };



    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        edit_date = (EditText)rootView.findViewById(R.id.edit_date);
        edit_date.setOnClickListener(this);
        edit_date.setTextIsSelectable(true);
        edit_date.setKeyListener(null);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
    @Override
    public void onClick(View v) {
        if (edit_date == v){
            showDatePicker();
        }


    }


}
