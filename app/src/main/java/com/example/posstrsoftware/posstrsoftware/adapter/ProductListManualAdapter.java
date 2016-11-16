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
import com.example.posstrsoftware.posstrsoftware.model.ProductList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 11/15/2016.
 */

public class ProductListManualAdapter extends BaseAdapter  implements Filterable{
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<ProductList> mProductList;
    ArrayList<ProductList> filterList;
    CustomFilter filter;

    public ProductListManualAdapter(Activity activity, ArrayList<ProductList> mProductList) {
        this.mProductList = mProductList;
        this.activity = activity;
        this.filterList = mProductList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.list_item_productlistmanual,null);
        TextView textview = (TextView) v.findViewById(R.id.txt_Group);
        TextView textview1 = (TextView) v.findViewById(R.id.txt_name_product);
        ProductList productList = mProductList.get(position);
        textview.setText(productList.getGroupList().getGroupText());
        textview1.setText(productList.getProductText());


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
                ArrayList<ProductList> filters = new ArrayList<>();
                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getProductText().toUpperCase().contains(constraint)){
                        ProductList u=new ProductList(filterList.get(i).getId(),filterList.get(i).getProductText(),filterList.get(i).getGroupList());
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
            mProductList = (ArrayList<ProductList>) results.values;
            notifyDataSetChanged();
        }
    }
}
