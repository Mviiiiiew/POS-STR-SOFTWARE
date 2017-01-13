package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/27/2016.
 */

public class CompanyAdapter extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;

    ArrayList<CompanyList> mCompanyLists;

    public CompanyAdapter(Activity activity,ArrayList<CompanyList> mGruopList) {
        this.mCompanyLists = mGruopList;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mCompanyLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mCompanyLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCompanyLists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            v = inflater.inflate(R.layout.list_item_company, null);
        }
        TextView txt_Name_Company = (TextView) v.findViewById(R.id.txt_Name_Company);
        TextView txt_Address_Company = (TextView) v.findViewById(R.id.txt_Address_Company);
        TextView txt_Telephone = (TextView) v.findViewById(R.id.txt_Telephone);
        TextView txt_Name_TAXID = (TextView) v.findViewById(R.id.txt_Name_TAXID);
        TextView txt_Name_DivisionName = (TextView) v.findViewById(R.id.txt_Name_DivisionName);
        TextView txt_Name_POSMachineID = (TextView) v.findViewById(R.id.txt_Name_POSMachineID);
        TextView txt_Name_RegisterID = (TextView) v.findViewById(R.id.txt_Name_RegisterID);
        TextView txt_Name_ENDbillText = (TextView) v.findViewById(R.id.txt_Name_ENDbillText);
        TextView txt_Name_VATRate = (TextView) v.findViewById(R.id.txt_Name_VATRate);

        CompanyList companyList = mCompanyLists.get(position);

        txt_Name_Company.setText(companyList.getCompanyName());
        txt_Address_Company.setText(companyList.getCompanyAddress());
        txt_Telephone.setText(companyList.getTelephone());
        txt_Name_TAXID.setText(companyList.getTAXID());
        txt_Name_DivisionName.setText(companyList.getDivisionName());
        txt_Name_POSMachineID.setText(companyList.getPOSMachineID());
        txt_Name_RegisterID.setText(companyList.getRegisterID());
        txt_Name_ENDbillText.setText(companyList.getENDbillText());
        txt_Name_VATRate.setText(companyList.getVATRate().toString());

        return v;


    }
}
