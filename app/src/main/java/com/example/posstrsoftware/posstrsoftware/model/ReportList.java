package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by Wasabi on 11/28/2016.
 */

public class ReportList {

    private int id;
    private String NameProduct;
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
