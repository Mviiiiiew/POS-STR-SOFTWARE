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
 * Created by Wasabi on 10/21/2016.
 */

public class SaleProductAdapter extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<ProductList> mSaleProductList;

    public SaleProductAdapter(Activity activity,ArrayList<ProductList> mSaleProductList) {
        this.mSaleProductList = mSaleProductList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mSaleProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSaleProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mSaleProductList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.list_item_saleproduct,null);
        TextView textview = (TextView) v.findViewById(R.id.txt_id_barcode);
        ProductList productList = mSaleProductList.get(position);
        textview.setText(productList.getProductText());


        return v;
    }

}
