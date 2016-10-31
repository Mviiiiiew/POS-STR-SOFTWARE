package com.example.posstrsoftware.posstrsoftware.model;

import android.widget.BaseAdapter;

/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleList  {

    private  int id;
    private String ProductSale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductSale() {
        return ProductSale;
    }

    public void setProductSale(String productSale) {
        ProductSale = productSale;
    }
}
