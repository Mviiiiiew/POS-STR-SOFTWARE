package com.example.posstrsoftware.posstrsoftware.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.util.SelectDateFragment;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportProductFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_print;
    ButtonRectangle btn_back;
    RadioGroup radiogroup_date;
    RadioButton radio_date_day;
    RadioButton radio_date_between;
    EditText edit_date_day;
    EditText edit_date_one;
    EditText edit_date_two;

    public ReportProductFragment() {
        super();
    }

    public static ReportProductFragment newInstance() {
        ReportProductFragment fragment = new ReportProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_product, container, false);
        initInstances(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Date();
    }

    private void Date() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        edit_date_day.setText(date);

    }



    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        radiogroup_date = (RadioGroup)rootView.findViewById(R.id.radiogroup_date);
        radio_date_between=(RadioButton)rootView.findViewById(R.id.radio_date_between);
        radio_date_day=(RadioButton)rootView.findViewById(R.id.radio_date_day);
        edit_date_day=(EditText)rootView.findViewById(R.id.edit_date_day);
        edit_date_one=(EditText)rootView.findViewById(R.id.edit_date_one);
        edit_date_two=(EditText)rootView.findViewById(R.id.edit_date_two);
        btn_print = (ButtonRectangle)rootView.findViewById(R.id.btn_print);
        btn_back = (ButtonRectangle)rootView.findViewById(R.id.btn_back);
        btn_print.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        edit_date_day.setOnClickListener(this);
        edit_date_day.setTextIsSelectable(true);
        edit_date_day.setKeyListener(null);

        edit_date_one.setOnClickListener(this);
        edit_date_one.setTextIsSelectable(true);
        edit_date_one.setKeyListener(null);

        edit_date_two.setOnClickListener(this);
        edit_date_two.setTextIsSelectable(true);
        edit_date_two.setKeyListener(null);

        btn_back.setRippleSpeed(40);
        btn_print.setRippleSpeed(40);
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
        if(btn_print == v){
            Toast.makeText(getActivity(),"PRINT",Toast.LENGTH_SHORT).show();
        }else if(btn_back == v){
            getActivity().finish();
        }else if(edit_date_day == v){
            showDatePicker();
        }else if(edit_date_one == v){
            showDatePickerOne();
        }else if(edit_date_two == v){
            showDatePickerTwo();
        }

    }

    private void showDatePickerTwo() {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        selectDateFragment.setCallBack(ondatetwo);
        selectDateFragment.show(getActivity().getSupportFragmentManager(),"DatePickerTwo");
    }
    DatePickerDialog.OnDateSetListener ondatetwo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (dayOfMonth < 10 && month < 10) {
                edit_date_two.setText(year + "/" + "0" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth < 10 && month >= 10) {
                edit_date_two.setText(year + "/" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth >= 10 && month < 10) {
                edit_date_two.setText(year + "/" +"0"+ (month + 1) + "/"  + dayOfMonth);
            } else {
                edit_date_two.setText(year + "/" + (month + 1) + "/" + dayOfMonth);

            }
        }
    };


    private void showDatePickerOne() {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        selectDateFragment.setCallBack(ondateone);
        selectDateFragment.show(getActivity().getSupportFragmentManager(),"DatePickerOne");
    }
    DatePickerDialog.OnDateSetListener ondateone = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (dayOfMonth < 10 && month < 10) {
                edit_date_one.setText(year + "/" + "0" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth < 10 && month >= 10) {
                edit_date_one.setText(year + "/" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth >= 10 && month < 10) {
                edit_date_one.setText(year + "/" +"0"+ (month + 1) + "/"  + dayOfMonth);
            } else {

                edit_date_one.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
            }
    }
    };

    private void showDatePicker() {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        selectDateFragment.setCallBack(ondate);
        selectDateFragment.show(getActivity().getSupportFragmentManager(),"DatePicker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (dayOfMonth < 10 && month < 10) {
                edit_date_day.setText(year + "/" + "0" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth < 10 && month >= 10) {
                edit_date_day.setText(year + "/" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth >= 10 && month < 10) {
                edit_date_day.setText(year + "/" +"0"+ (month + 1) + "/"  + dayOfMonth);
            } else {

                edit_date_day.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
            }
        }
    };
}
