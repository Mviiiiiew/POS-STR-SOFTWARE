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
 * Created by Wasabi on 10/18/2016.
 */

/*public class spinnerGroupAdapter extends BaseAdapter {
    private static Activity activity;
    private  static LayoutInflater inflater;
    ArrayList<GroupList> mGroupLists;

    public spinnerGroupAdapter(Activity activity,ArrayList<GroupList> mGroupLists) {
        this.mGroupLists = mGroupLists;
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mGroupLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mGroupLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGroupLists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        v = inflater.inflate(R.layout.list_group_spinner,null);
        TextView textview = (TextView) v.findViewById(R.id.txt_group_name_spinner);
        GroupList groupList = mGroupLists.get(position);
        textview.setText(groupList.getGroupText());


        return v;
    }
}*/
