package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class ProductAdapter extends BaseAdapter {

    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<ProductList> mProductList;

    public ProductAdapter(Activity activity, ArrayList<ProductList> mProductList) {
        this.mProductList = mProductList;
        this.activity = activity;
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
        v = inflater.inflate(R.layout.list_item_product,null);
        TextView textview = (TextView) v.findViewById(R.id.txt_name_product);
        TextView textview1 = (TextView)  v.findViewById(R.id.txt_id_product);
        TextView textview2 = (TextView) v.findViewById(R.id.txt_name_price);
        TextView textview3 = (TextView)v.findViewById(R.id.txt_name_unit);
        TextView textview4 = (TextView)v.findViewById(R.id.txt_name_group);
        ProductList productList = mProductList.get(position);
        textview.setText(productList.getProductText());
        textview1.setText(productList.getId()+"");
        textview2.setText(productList.getProductprice()+"");
        textview3.setText(productList.getUnitList().getUnitText());
        textview4.setText(productList.getGroupList().getGroupText());

        return v;
    }
}