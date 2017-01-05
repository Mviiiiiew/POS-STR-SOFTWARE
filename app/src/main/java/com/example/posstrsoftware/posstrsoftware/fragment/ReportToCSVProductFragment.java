package com.example.posstrsoftware.posstrsoftware.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.util.SelectDateFragment;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportToCSVProductFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_print;
    ButtonRectangle btn_back;
    RadioGroup radiogroup_date;
    RadioButton radio_date_day;
    RadioButton radio_date_between;
    EditText edit_date_day;
    EditText edit_date_one;
    EditText edit_date_two;
    String date;
    String datetwo;
    String dateone;

    public ReportToCSVProductFragment() {
        super();
    }

    public static ReportToCSVProductFragment newInstance() {
        ReportToCSVProductFragment fragment = new ReportToCSVProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_to_csvproduct, container, false);
        initInstances(rootView);
        edit_date_one.setFocusableInTouchMode(false);
        edit_date_two.setFocusableInTouchMode(false);
        edit_date_one.setEnabled(false);
        edit_date_two.setEnabled(false);
        radiogroup_date.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radiogroup_date.getCheckedRadioButtonId()) {
                    case R.id.radio_date_day:
                        edit_date_day.setEnabled(true);
                        edit_date_one.setEnabled(false);
                        edit_date_two.setEnabled(false);
                        break;
                    case R.id.radio_date_between:
                        edit_date_two.setEnabled(true);
                        edit_date_one.setEnabled(true);
                        edit_date_day.setEnabled(false);
                        break;
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Date();
    }

    private void Date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        edit_date_day.setText(date);
        edit_date_one.setText(date);
        edit_date_two.setText(date);

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
            ReportDAO reportDAO = new ReportDAO(getActivity());
            reportDAO.open();
            switch (radiogroup_date.getCheckedRadioButtonId()) {

                case R.id.radio_date_day:
                    date = edit_date_day.getText().toString();
                    String x = date.replaceAll("/", "");
                    reportDAO.exportDataBaseProduct(x);
                    reportDAO.close();
                    Toast.makeText(getActivity(), "บันทึกเสร็จสิ้น", Toast.LENGTH_SHORT).show();

                    break;


                case R.id.radio_date_between:
                    dateone = edit_date_one.getText().toString();
                    datetwo = edit_date_two.getText().toString();
                    date = edit_date_day.getText().toString();
                    int oneday = Integer.parseInt(dateone.replaceAll("/", ""));
                    Log.d(oneday + "", "one");
                    int twoday = Integer.parseInt(datetwo.replaceAll("/", ""));
                    Log.d(twoday + "", "two");
                    if (oneday < twoday) {
                        reportDAO.exportDataBaseProductOneTwo(oneday + "", twoday + "");
                        reportDAO.close();
                        Toast.makeText(getActivity(), "บันทึกเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                    } else if (oneday > twoday) {
                        reportDAO.exportDataBaseProductTwoOne(oneday + "", twoday + "");
                        reportDAO.close();
                        Toast.makeText(getActivity(), "บันทึกเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                    }else{
                        reportDAO.exportDataBaseProductOneTwo(oneday + "", twoday + "");
                        reportDAO.close();
                        Toast.makeText(getActivity(), "บันทึกเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                    }

                    break;

            }
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
