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
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class ProductAdapter extends BaseAdapter implements Filterable {

    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<ProductList> mProductList;
    ArrayList<ProductList> filterList;
    CustomFilter filter;

    public ProductAdapter(Activity activity, ArrayList<ProductList> mProductList) {
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
        v = inflater.inflate(R.layout.list_item_product, null);
        TextView txt_name_product = (TextView) v.findViewById(R.id.txt_name_product);
        TextView txt_id_product = (TextView) v.findViewById(R.id.txt_id_product);
        TextView txt_name_price = (TextView) v.findViewById(R.id.txt_name_price);
        TextView txt_name_unit = (TextView) v.findViewById(R.id.txt_name_unit);
        TextView txt_name_group = (TextView) v.findViewById(R.id.txt_name_group);
        TextView txt_id_barcode = (TextView) v.findViewById(R.id.txt_id_barcode);
        TextView txt_mCost = (TextView)v.findViewById(R.id.txt_mCost);

        ProductList productList = mProductList.get(position);
        txt_name_product.setText(productList.getProductText());
        txt_id_product.setText(productList.getId() + "");
        txt_name_price.setText(formatAmount.formatAmountDouble(Double.valueOf(productList.getProductprice() + "")));
        txt_name_unit.setText(productList.getUnitList().getUnitText());
        txt_name_group.setText(productList.getGroupList().getGroupText());
        txt_id_barcode.setText(productList.getBarcode() + "");
        txt_mCost.setText(formatAmount.formatAmountDouble(Double.valueOf(productList.getCost()+"")));
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

                ArrayList<ProductList> filters = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getProductText().toUpperCase().contains(constraint)) {
                        ProductList u = new ProductList(filterList.get(i).getId(), filterList.get(i).getProductText(), filterList.get(i).getProductprice(), filterList.get(i).getGroupList(), filterList.get(i).getUnitList(), filterList.get(i).getBarcode());

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
            mProductList = (ArrayList<ProductList>) results.values;
            notifyDataSetChanged();
        }
    }
}