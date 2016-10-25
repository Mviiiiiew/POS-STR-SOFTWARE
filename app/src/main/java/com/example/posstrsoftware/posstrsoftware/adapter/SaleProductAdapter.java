package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.SaleProductList;

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
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        v = inflater.inflate(R.layout.list_item_productlist,null);
        TextView textView1 = (TextView) v.findViewById(R.id.txt_name_product);
        TextView textView2 = (TextView)v.findViewById(R.id.txt_name_price);
        ProductList productList = mSaleProductList.get(position);
        textView1.setText(productList.getProductText());
        textView2.setText(productList.getProductprice()+"");


        return v;
    }

}
