package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by Wasabi on 11/28/2016.
 */

public class ReportList {

private int sale_masterid ;
    private  String date;
    private  double discount;
    private  int amount;

    public int getSale_masterid() {
        return sale_masterid;
    }

    public void setSale_masterid(int sale_masterid) {
        this.sale_masterid = sale_masterid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
