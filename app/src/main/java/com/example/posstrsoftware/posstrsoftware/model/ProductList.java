package com.example.posstrsoftware.posstrsoftware.model;

import java.io.Serializable;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class ProductList implements Serializable {

    private int id;
    private Double productpricesumvat;
    private String productText;
    private Double productprice;
    private GroupList groupList;
    private UnitList unitList;
    private String barcode;
    private String checkvat;
    private Double Cost;
    private String SymbolVat;
    private Double ValueVat;

    public Double getValueVat() {
        return ValueVat;
    }

    public void setValueVat(Double valueVat) {
        ValueVat = valueVat;
    }

    public ProductList() {

    }

    public ProductList(int id, String productText, Double productprice, GroupList groupList, UnitList unitList, Double cost, String barcode, String symbolVat) {
        this.id = id;
        this.productpricesumvat = productpricesumvat;
        this.productText = productText;
        this.productprice = productprice;
        this.groupList = groupList;
        this.unitList = unitList;
        this.barcode = barcode;
        this.checkvat = checkvat;
        Cost = cost;
        SymbolVat = symbolVat;
    }

    public ProductList(int id, String productText, GroupList groupList) {
        this.id = id;
        this.productText = productText;
        this.groupList = groupList;
    }


    public Double getProductpricesumvat() {
        return productpricesumvat;
    }

    public void setProductpricesumvat(Double productpricesumvat) {
        this.productpricesumvat = productpricesumvat;
    }

    public String getSymbolVat() {
        return SymbolVat;
    }

    public void setSymbolVat(String symbolVat) {
        SymbolVat = symbolVat;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }

    public String getCheckvat() {
        return checkvat;
    }

    public void setCheckvat(String checkvat) {
        this.checkvat = checkvat;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public GroupList getGroupList() {
        return groupList;
    }

    public void setGroupList(GroupList groupList) {
        this.groupList = groupList;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
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
