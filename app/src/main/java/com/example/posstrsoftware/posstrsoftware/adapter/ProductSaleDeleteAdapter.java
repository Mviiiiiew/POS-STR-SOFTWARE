package com.example.posstrsoftware.posstrsoftware.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleDeleteAdapter extends BaseAdapter implements Filterable {

    private static Activity activity;
    private static LayoutInflater inflater;
    private ArrayList<ProductSaleList> mProductSaleLists;
    ArrayList<ProductSaleList> filterList;
    CustomFilter filter;


    public ProductSaleDeleteAdapter(Activity activity, ArrayList<ProductSaleList> mProductSaleLists) {
        this.mProductSaleLists = mProductSaleLists;
        this.activity = activity;
        this.filterList = mProductSaleLists;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        View v = convertView;
        if (convertView == null) {
            v = inflater.inflate(R.layout.list_item_productsaledelete, null);
        }
        TextView txt_name_productsale = (TextView) v.findViewById(R.id.txt_name_productsale);
        TextView txt_name_productprice = (TextView) v.findViewById(R.id.txt_name_productprice);
        ProductSaleList productSaleList = mProductSaleLists.get(position);
        txt_name_productsale.setText(productSaleList.getProductSale());
        txt_name_productprice.setText(formatAmount.formatAmountDouble(Double.valueOf(productSaleList.getPrice())));

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

                ArrayList<ProductSaleList> filters = new ArrayList<>();
                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getProductSale().toUpperCase().contains(constraint)){
                        ProductSaleList u=new ProductSaleList(filterList.get(i).getId(),filterList.get(i).getProductSale(),filterList.get(i).getPrice());

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
            mProductSaleLists = (ArrayList<ProductSaleList>) results.values;
            notifyDataSetChanged();
        }
    }
}


