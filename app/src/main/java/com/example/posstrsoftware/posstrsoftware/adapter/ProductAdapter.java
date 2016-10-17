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
        TextView textView1 = (TextView)  v.findViewById(R.id.txt_id_product);
        ProductList productList = mProductList.get(position);
        textview.setText(productList.getProductText());
        textView1.setText(productList.getId()+"");
        return v;
    }
}