package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.UpdateCompanyActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.CompanyAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;
import com.gc.materialdesign.views.ButtonIcon;
import com.gc.materialdesign.views.ButtonRectangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CompanySetFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_UpdateCompany;
    ListView listview_Company;
    ImageButton btn_back;





    public CompanySetFragment() {
        super();
    }

    public static CompanySetFragment newInstance() {
        CompanySetFragment fragment = new CompanySetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_company, container, false);
        initInstances(rootView);


        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_UpdateCompany = (ButtonRectangle)rootView.findViewById(R.id.btn_UpdateCompany);
        btn_back = (ImageButton)rootView.findViewById(R.id.btn_back);
        listview_Company = (ListView)rootView.findViewById(R.id.listview_Company);
        btn_UpdateCompany.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_UpdateCompany.setRippleSpeed(25);

    }

    @Override
    public void onResume() {

        super.onResume();
        final CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();
        final ArrayList<CompanyList> myCompanyLists = companyDAO.getAllCompany();

        companyDAO.close();
        final CompanyAdapter objAdapter = new CompanyAdapter(getActivity(),myCompanyLists);
        listview_Company.setAdapter(objAdapter);


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
        if(btn_UpdateCompany == v) {
            final CompanyDAO companyDAO = new CompanyDAO(getActivity());
            companyDAO.open();
            final ArrayList<CompanyList> myCompanyLists = companyDAO.getAllCompany();
            companyDAO.close();
            final CompanyAdapter objAdapter = new CompanyAdapter(getActivity(), myCompanyLists);
            Intent intent = new Intent(getActivity(), UpdateCompanyActivity.class);
            intent.putExtra("editCompany", (Serializable) objAdapter.getItem(0));
            startActivity(intent);

        }else if(btn_back == v){
            getActivity().finish();
        }


    }
}
