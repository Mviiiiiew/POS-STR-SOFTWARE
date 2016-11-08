package com.example.posstrsoftware.posstrsoftware.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.BaseAdapter;


/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleList {

    private int id;
    private String amount;
    private String ProductSale;
    private String price;
    boolean checkbox;


    public ProductSaleList() {

    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductSale() {
        return ProductSale;
    }

    public void setProductSale(String prodcutSale) {
        ProductSale = prodcutSale;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
