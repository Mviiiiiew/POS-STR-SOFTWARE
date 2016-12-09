package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.ReportDayActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportToCSVDayActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportToCSVProductActivity;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportToPrintFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_Report_Print_day;
    ButtonRectangle btn_Report_Print_product;
    ImageButton btn_back;

    public ReportToPrintFragment() {
        super();
    }

    public static ReportToPrintFragment newInstance() {
        ReportToPrintFragment fragment = new ReportToPrintFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_print, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton)rootView.findViewById(R.id.btn_back);
        btn_Report_Print_day = (ButtonRectangle)rootView.findViewById(R.id.btn_Report_Print_day);
        btn_Report_Print_product = (ButtonRectangle)rootView.findViewById(R.id.btn_Report_Print_product);
        btn_Report_Print_day.setRippleSpeed(40);
        btn_Report_Print_product.setRippleSpeed(40);
        btn_Report_Print_product.setOnClickListener(this);
        btn_Report_Print_day.setOnClickListener(this);
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
        if (btn_Report_Print_day == v) {
            Intent intent = new Intent(getActivity(), ReportDayActivity.class);
            startActivity(intent);

        }else if (btn_Report_Print_product == v){
            Intent intent = new Intent(getActivity(), ReportProductActivity.class);
            startActivity(intent);
        }else if (btn_back == v){
            getActivity().finish();
        }
    }
}
