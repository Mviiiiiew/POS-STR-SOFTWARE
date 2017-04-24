package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.ReportToCSVDayActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportToCSVProductActivity;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportToCSVFragment extends Fragment implements View.OnClickListener {
    Button btn_Report_CSV_day;
    Button btn_Report_CSV_product;
    ImageButton btn_back;

    public ReportToCSVFragment() {
        super();
    }

    public static ReportToCSVFragment newInstance() {
        ReportToCSVFragment fragment = new ReportToCSVFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_csv, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton)rootView.findViewById(R.id.btn_back);
        btn_Report_CSV_day = (Button) rootView.findViewById(R.id.btn_Report_CSV_day);
        btn_Report_CSV_product = (Button)rootView.findViewById(R.id.btn_Report_CSV_product);
       // btn_Report_CSV_day.setRippleSpeed(40);
        //btn_Report_CSV_product.setRippleSpeed(40);
        btn_Report_CSV_product.setOnClickListener(this);
        btn_Report_CSV_day.setOnClickListener(this);
        btn_back.setOnClickListener(this);

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
        if (btn_Report_CSV_day == v) {
            Intent intent = new Intent(getActivity(), ReportToCSVDayActivity.class);
            startActivity(intent);

        }else if (btn_Report_CSV_product == v){
            Intent intent = new Intent(getActivity(), ReportToCSVProductActivity.class);
            startActivity(intent);
        }else if (btn_back == v){
            getActivity().finish();
        }
    }
}
