package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by MAN on 12/4/2016.
 */

public class SaleMasterList {

    private int id;
    private String date;
    private  Double discount;
    private  int company;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }
}
