package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class ProductList {

    private int id;
    private String productText;
    private UnitList unitList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public UnitList getUnitList() {
        return unitList;
    }

    public void setUnitList(UnitList unitList) {
        this.unitList = unitList;
    }
}
