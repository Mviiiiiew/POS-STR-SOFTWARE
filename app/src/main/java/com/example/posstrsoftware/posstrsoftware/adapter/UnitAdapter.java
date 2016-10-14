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
 * Created by Wasabi on 10/14/2016.
 */

public class UnitAdapter extends BaseAdapter {

    private static Activity activity;
    private static LayoutInflater inflater;

    ArrayList<UnitList> mUnitList;

    public UnitAdapter(Activity activity, ArrayList<UnitList> mUnitList) {
        this.mUnitList = mUnitList;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mUnitList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUnitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mUnitList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            v = inflater.inflate(R.layout.list_item_unit, null);
        }
        TextView txt_name_unit = (TextView) v.findViewById(R.id.txt_name_unit);
        TextView txt_id_unit = (TextView) v.findViewById(R.id.txt_id_unit);
        UnitList unitList = mUnitList.get(position);
        txt_name_unit.setText(unitList.getUnitText());
        txt_id_unit.setText(unitList.getId() + "");

        return v;

    }
}
