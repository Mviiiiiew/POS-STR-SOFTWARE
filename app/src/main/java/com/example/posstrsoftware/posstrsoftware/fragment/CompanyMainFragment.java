package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.CompanySetActivity;
import com.example.posstrsoftware.posstrsoftware.activity.UpdateCompanyActivity;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;

import java.io.Serializable;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CompanyMainFragment extends Fragment implements View.OnClickListener {

    Button btn_SettingCompany;


    public CompanyMainFragment() {
        super();
    }

    public static CompanyMainFragment newInstance() {
        CompanyMainFragment fragment = new CompanyMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_company, container, false);
        initInstances(rootView);

        return rootView;

    }



    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        btn_SettingCompany = (Button)rootView.findViewById(R.id.btn_SettingCompany);
        btn_SettingCompany.setOnClickListener(this);

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

        Intent intent = new Intent(getActivity(), CompanySetActivity.class);
        startActivity(intent);



    }
}
