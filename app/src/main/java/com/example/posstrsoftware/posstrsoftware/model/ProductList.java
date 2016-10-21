package com.example.posstrsoftware.posstrsoftware.model;

import java.io.Serializable;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class ProductList implements Serializable {

    private int id;
    private String productText;
    private int productprice;
    private GroupList groupList;
    private UnitList unitList;
    private int barcode;

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public GroupList getGroupList() {
        return groupList;
    }

    public void setGroupList(GroupList groupList) {
        this.groupList = groupList;
    }

    public int getProductprice() {
        return productprice;
    }

    public void setProductprice(int productprice) {
        this.productprice = productprice;
    }


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
