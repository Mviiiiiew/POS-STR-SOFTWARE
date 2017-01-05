package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.GroupList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class GroupAdapter extends BaseAdapter implements Filterable {
    private static Activity activity;
    private static LayoutInflater inflater;

    ArrayList<GroupList> mGruopList;
    ArrayList<GroupList> filterList;
    CustomFilter filter;


    public GroupAdapter(Activity activity, ArrayList<GroupList> mGruopList) {
        this.mGruopList = mGruopList;
        this.activity = activity;
        this.filterList = mGruopList;

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
        GroupList groupList = mGruopList.get(position);
        txt_name_group.setText(groupList.getGroupText());


        return v;

    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }


        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<GroupList> filters = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getGroupText().toUpperCase().contains(constraint)) {
                        GroupList u = new GroupList(filterList.get(i).getGroupText());
                        filters.add(u);
                    }
                }

                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mGruopList = (ArrayList<GroupList>) results.values;
            notifyDataSetChanged();
        }
    }
}
