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

import com.POSD.controllers.PrinterController;
import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.util.SelectDateFragment;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportToCSVDayFragment extends Fragment implements View.OnClickListener {


    EditText edit_date_day;
    EditText edit_date_one;
    EditText edit_date_two;
    RadioGroup radiogroup_date;
    RadioButton radio_date_day;
    RadioButton radio_date_between;
    ButtonRectangle btn_save;
    ButtonRectangle btn_back;
    String date;
    String dateone;
    String datetwo;


    public ReportToCSVDayFragment() {
        super();
    }

    public static ReportToCSVDayFragment newInstance() {
        ReportToCSVDayFragment fragment = new ReportToCSVDayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_to_csvday, container, false);
        initInstances(rootView);
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

        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(java.util.Calendar.getInstance().getTime());
        edit_date_day.setText(date);
        edit_date_one.setText(date);
        edit_date_two.setText(date);

    }

    private void showDatePicker() {
        SelectDateFragment date = new SelectDateFragment();
        date.setCallBack(ondate);
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        /*    Toast.makeText(getActivity(), String.valueOf(year) + "-" + String.valueOf(month + 1)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_SHORT).show();*/
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


    private void showDatePickerOne() {
        SelectDateFragment date = new SelectDateFragment();
        date.setCallBack(ondateOne);
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondateOne = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            /*Toast.makeText(getActivity(), String.valueOf(year) + "-" + String.valueOf(month + 1)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_SHORT).show();*/
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

    private void showDatePickerTwo() {
        SelectDateFragment date = new SelectDateFragment();
        date.setCallBack(ondateTwo);
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondateTwo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          /*  Toast.makeText(getActivity(), String.valueOf(year) + "-" + String.valueOf(month + 1)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_SHORT).show();*/
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


    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        radiogroup_date = (RadioGroup) rootView.findViewById(R.id.radiogroup_date);
        radio_date_day = (RadioButton) rootView.findViewById(R.id.radio_date_day);
        radio_date_between = (RadioButton) rootView.findViewById(R.id.radio_date_between);
        edit_date_day = (EditText) rootView.findViewById(R.id.edit_date_day);
        edit_date_one = (EditText) rootView.findViewById(R.id.edit_date_one);
        edit_date_two = (EditText) rootView.findViewById(R.id.edit_date_two);
        edit_date_one.setOnClickListener(this);
        edit_date_one.setTextIsSelectable(true);
        edit_date_one.setKeyListener(null);
        edit_date_two.setOnClickListener(this);
        edit_date_two.setTextIsSelectable(true);
        edit_date_two.setKeyListener(null);
        edit_date_day.setOnClickListener(this);
        edit_date_day.setTextIsSelectable(true);
        edit_date_day.setKeyListener(null);
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_save.setRippleSpeed(40);
        btn_back.setRippleSpeed(40);


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
        if (edit_date_day == v) {
            showDatePicker();
        } else if (edit_date_one == v) {
            showDatePickerOne();
        } else if (edit_date_two == v) {
            showDatePickerTwo();
        } else if (btn_back == v) {
            getActivity().finish();
        } else if (btn_save == v) {
            ReportDAO reportDAO = new ReportDAO(getActivity());
            reportDAO.open();
            switch (radiogroup_date.getCheckedRadioButtonId()) {

                case R.id.radio_date_day:
                    date = edit_date_day.getText().toString();
                    String x = date.replaceAll("/", "");
                    reportDAO.exportDataBaseDayDaily(x);
                    reportDAO.close();
                    Toast.makeText(getActivity(), x, Toast.LENGTH_SHORT).show();

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
                        reportDAO.exportDataBaseBetweenDailyOneTwo(oneday + "", twoday + "");
                        reportDAO.close();
                    } else if (oneday > twoday) {
                        reportDAO.exportDataBaseBetweenDailyTwoOne(oneday + "", twoday + "");
                        reportDAO.close();
                    }

                    break;

            }
        }


    }


}
