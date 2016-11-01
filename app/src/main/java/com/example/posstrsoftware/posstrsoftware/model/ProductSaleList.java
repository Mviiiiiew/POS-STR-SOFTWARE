package com.example.posstrsoftware.posstrsoftware.model;

import android.widget.BaseAdapter;



/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleList  {

    private  int id;
    private String ProductSale;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductSale() {
        return ProductSale;
    }


    public void setProductSale(String prodcutSale){
        ProductSale = prodcutSale;
    }
}
