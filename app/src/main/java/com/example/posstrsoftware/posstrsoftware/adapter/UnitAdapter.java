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
import com.example.posstrsoftware.posstrsoftware.model.UnitList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class UnitAdapter extends BaseAdapter implements Filterable {

    private static Activity activity;
    private static LayoutInflater inflater;

    ArrayList<UnitList> mUnitList;
    ArrayList<UnitList> filterList;
    CustomFilter filter;

    public UnitAdapter(Activity activity, ArrayList<UnitList> mUnitList) {
        this.mUnitList = mUnitList;
        this.activity = activity;
        this.filterList = mUnitList;

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

    @Override
    public Filter getFilter() {

        if(filter == null){
            filter = new CustomFilter();
        }


        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length()>0)
            {
                constraint = constraint.toString().toUpperCase();

                ArrayList<UnitList> filters = new ArrayList<>();
                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getUnitText().toUpperCase().contains(constraint)){
                        UnitList u=new UnitList(filterList.get(i).getId(),filterList.get(i).getUnitText());
                        filters.add(u);
                    }
                }
                results.count = filters.size();
                results.values=filters;
            }else
            {
                results.count = filterList.size();
                results.values=filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mUnitList = (ArrayList<UnitList>) results.values;
            notifyDataSetChanged();
        }
    }
}
