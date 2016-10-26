package com.example.posstrsoftware.posstrsoftware.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.CompanyMainActivity;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class UpdateCompanyFragment extends Fragment implements View.OnClickListener {
    EditText editText_CompanyName;
    EditText editText_CompanyAddress;
    Button btn_UpdateCompany;

    public UpdateCompanyFragment() {
        super();
    }

    public static UpdateCompanyFragment newInstance() {
        UpdateCompanyFragment fragment = new UpdateCompanyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_update_company, container, false);
        initInstances(rootView);
        String n = getActivity().getIntent().getStringExtra("Company");
        editText_CompanyName.setText(n);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        editText_CompanyAddress = (EditText) rootView.findViewById(R.id.editText_CompanyAddress);
        editText_CompanyName = (EditText) rootView.findViewById(R.id.editText_CompanyName);
        btn_UpdateCompany = (Button) rootView.findViewById(R.id.btn_UpdateCompany);
        btn_UpdateCompany.setOnClickListener(this);


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
        Intent reture = new Intent();
        reture.putExtra("result",editText_CompanyAddress.getText().toString());
        getActivity().setResult(Activity.RESULT_OK,reture);
        getActivity().finish();


    }
}
