package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.UnitList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/18/2016.
 */

public class spinnerUnitAdapter extends BaseAdapter {
    private  static Activity activity;
    private  static LayoutInflater inflater;
    ArrayList<UnitList> mUnitListSpinner;

    public spinnerUnitAdapter(Activity activity,ArrayList<UnitList> mUnitListSpinner) {
        this.mUnitListSpinner = mUnitListSpinner;
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mUnitListSpinner.size();
    }

    @Override
    public Object getItem(int position) {
        return mUnitListSpinner.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mUnitListSpinner.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.list_unit_spinner,null);
        TextView textview = (TextView) v.findViewById(R.id.txt_unit_name_spinner);
        UnitList unitList = mUnitListSpinner.get(position);
        textview.setText(unitList.getUnitText());
        return v;
    }
}
