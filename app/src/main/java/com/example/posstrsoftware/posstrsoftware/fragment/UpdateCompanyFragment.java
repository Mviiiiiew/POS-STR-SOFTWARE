package com.example.posstrsoftware.posstrsoftware.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.CompanyMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.CompanySetActivity;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class UpdateCompanyFragment extends Fragment implements View.OnClickListener {
    EditText editText_CompanyName;
    EditText editText_CompanyAddress;
    EditText editText_Telephone;
    EditText editText_Name_TAXID;
    EditText editText_Name_Division;
    EditText editText_Name_RegisterID;
    EditText editText_Name_ENDbillText;
    EditText editText_Name_VATRate;
    ButtonRectangle btn_UpdateCompany;
    ImageButton btn_back;

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
        CompanyList editCompanyList = (CompanyList) getActivity().getIntent().getSerializableExtra("editCompany");
        editText_CompanyName.setText(editCompanyList.getCompanyName());
        editText_CompanyAddress.setText(editCompanyList.getCompanyAddress());
        editText_Telephone.setText(editCompanyList.getTelephone());
        editText_Name_TAXID.setText(editCompanyList.getTAXID());
        editText_Name_Division.setText(editCompanyList.getDivisionName());
        editText_Name_RegisterID.setText(editCompanyList.getRegisterID());
        editText_Name_ENDbillText.setText(editCompanyList.getENDbillText());
        editText_Name_VATRate.setText(editCompanyList.getVATRate().toString());

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        editText_CompanyAddress = (EditText) rootView.findViewById(R.id.editText_CompanyAddress);
        editText_CompanyName = (EditText) rootView.findViewById(R.id.editText_CompanyName);
        editText_Telephone = (EditText) rootView.findViewById(R.id.editText_Telephone);
        editText_Name_TAXID = (EditText)rootView.findViewById(R.id.editText_Name_TAXID);
        editText_Name_Division = (EditText)rootView.findViewById(R.id.editText_Name_Division);
        editText_Name_RegisterID = (EditText)rootView.findViewById(R.id.editText_Name_RegisterID);
        editText_Name_ENDbillText = (EditText)rootView.findViewById(R.id.editText_Name_ENDbillText);
        editText_Name_VATRate = (EditText)rootView.findViewById(R.id.editText_Name_VATRate);
        btn_UpdateCompany = (ButtonRectangle) rootView.findViewById(R.id.btn_UpdateCompany);
        btn_back = (ImageButton)rootView.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        btn_UpdateCompany.setOnClickListener(this);
        btn_UpdateCompany.setRippleSpeed(25);


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
            if (editText_Name_VATRate.getText().toString().trim().replaceAll("", "").replaceAll("\\.", "").matches("")) {
               editText_Name_VATRate.setText("7.0");

                CompanyList companyList = new CompanyList();
                companyList.setCompanyName(String.valueOf(editText_CompanyName.getText()));
                companyList.setCompanyAddress(String.valueOf(editText_CompanyAddress.getText()));
                companyList.setTelephone(String.valueOf(editText_Telephone.getText()));
                companyList.setTAXID(String.valueOf(editText_Name_TAXID.getText()));
                companyList.setDivisionName(String.valueOf(editText_Name_Division.getText()));
                companyList.setRegisterID(String.valueOf(editText_Name_RegisterID.getText()));
                companyList.setENDbillText(String.valueOf(editText_Name_ENDbillText.getText()));
                companyList.setVATRate(Double.valueOf(editText_Name_VATRate.getText().toString()));

                CompanyDAO companyDAO = new CompanyDAO(getActivity());
                companyDAO.open();
                companyDAO.update(companyList);
                companyDAO.close();
                getActivity().finish();
            }else {

                TelephonyManager mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                CompanyList companyList = new CompanyList();
                companyList.setCompanyName(String.valueOf(editText_CompanyName.getText()));
                companyList.setCompanyAddress(String.valueOf(editText_CompanyAddress.getText()));
                companyList.setTelephone(String.valueOf(editText_Telephone.getText()));
                companyList.setTAXID(String.valueOf(editText_Name_TAXID.getText()));
                companyList.setDivisionName(String.valueOf(editText_Name_Division.getText()));
                companyList.setRegisterID(String.valueOf(editText_Name_RegisterID.getText()));
                companyList.setENDbillText(String.valueOf(editText_Name_ENDbillText.getText()));
                companyList.setVATRate(Double.valueOf(editText_Name_VATRate.getText().toString()));
                companyList.setPOSMachineID(mngr.getDeviceId());
                CompanyDAO companyDAO = new CompanyDAO(getActivity());
                companyDAO.open();
                companyDAO.update(companyList);
                companyDAO.close();
                getActivity().finish();
            }
            } else if (btn_back == v) {

                getActivity().finish();
            }



    }
}
