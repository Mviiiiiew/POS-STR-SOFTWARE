package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.ReportDayActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportToCSVActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ReportToPrintActivity;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportMainFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_Report_Print;
    ButtonRectangle btn_Report_CSV;

    public ReportMainFragment() {
        super();
    }

    public static ReportMainFragment newInstance() {
        ReportMainFragment fragment = new ReportMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_report, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_Report_Print = (ButtonRectangle)rootView.findViewById(R.id.btn_Report_Print);
        btn_Report_CSV = (ButtonRectangle)rootView.findViewById(R.id.btn_Report_CSV);
        btn_Report_CSV.setOnClickListener(this);
        btn_Report_Print.setOnClickListener(this);
        btn_Report_Print.setRippleSpeed(40);
        btn_Report_CSV.setRippleSpeed(40);
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
        if(btn_Report_Print == v) {
            Intent intent = new Intent(getActivity(), ReportToPrintActivity.class);
            startActivity(intent);
        }else if(btn_Report_CSV == v){
           Intent intent = new Intent(getActivity(), ReportToCSVActivity.class);
            startActivity(intent);


        }
    }
}
