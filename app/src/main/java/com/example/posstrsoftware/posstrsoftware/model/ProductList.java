package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class ProductList {

    private int id;
    private String productText;
    private int productprice;
    private GroupList groupList;
    private UnitList unitList;

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
