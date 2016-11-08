package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleDeleteAdapter extends BaseAdapter {

    private static Activity activity;
    private static LayoutInflater inflater;
    private ArrayList<ProductSaleList> mProductSaleLists;
    private ViewHolder viewHolder;
    private View v;


    public ProductSaleDeleteAdapter(Activity activity, ArrayList<ProductSaleList> mProductSaleLists) {
        this.mProductSaleLists = mProductSaleLists;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<ProductSaleList> getdata() {
        return mProductSaleLists;
    }

    @Override
    public int getCount() {
        return mProductSaleLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductSaleLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductSaleLists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        ProductSaleList productSaleList = mProductSaleLists.get(pos);
        v =convertView ;
        if (convertView == null) {
            v = inflater.inflate(R.layout.list_item_productsaledelete, null);
            viewHolder = new ViewHolder();
            viewHolder.txt_name_productsale = (TextView) v.findViewById(R.id.txt_name_productsale);
            viewHolder.txt_name_productprice = (TextView) v.findViewById(R.id.txt_name_productprice);
            viewHolder.checkbox = (CheckBox) v.findViewById(R.id.checkbox);
            v.setTag(viewHolder);

        } else
            viewHolder = (ViewHolder) v.getTag();
        viewHolder.txt_name_productsale.setText(productSaleList.getProductSale());
        viewHolder.txt_name_productprice.setText(formatAmount.formatAmountDouble(Double.valueOf(productSaleList.getPrice())));


        if (productSaleList.isCheckbox()) {
            viewHolder.checkbox.setChecked(true);


        } else {
            viewHolder.checkbox.setChecked(false);
        }

        return v;

    }



    public class ViewHolder {
        TextView txt_name_productsale;
        TextView txt_name_productprice;
        CheckBox checkbox;

    }
}
