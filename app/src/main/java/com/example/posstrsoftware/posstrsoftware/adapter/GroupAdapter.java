package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.GroupList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class GroupAdapter extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;

    ArrayList<GroupList> mGruopList;


    public GroupAdapter(Activity activity,ArrayList<GroupList> mGruopList) {
        this.mGruopList = mGruopList;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mGruopList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGruopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGruopList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            v = inflater.inflate(R.layout.list_item_group, null);
        }
        TextView txt_name_group = (TextView) v.findViewById(R.id.txt_name_group);
        TextView txt_id_group = (TextView) v.findViewById(R.id.txt_id_group);
        GroupList groupList = mGruopList.get(position);
        txt_name_group.setText(groupList.getGroupText());
        txt_id_group.setText(groupList.getId() + "");

        return v;

    }
}
