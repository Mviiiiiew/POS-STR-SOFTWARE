package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by MAN on 12/4/2016.
 */

public class SaleDetailList {
    private int saledetail;
    private int salemasterid;
    private int lineOn;
    private int productid;
    private int unitid;
    private int groupid;
    private String groupname;
    private int productamount;
    private Double productprice;
    private Double productpricetotal;
    private Double productcost;
    private Double productcosttotal;


    public int getSaledetail() {
        return saledetail;
    }

    public void setSaledetail(int saledetail) {
        this.saledetail = saledetail;
    }

    public int getSalemasterid() {
        return salemasterid;
    }

    public void setSalemasterid(int salemasterid) {
        this.salemasterid = salemasterid;
    }

    public int getLineOn() {
        return lineOn;
    }

    public void setLineOn(int lineOn) {
        this.lineOn = lineOn;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getProductamount() {
        return productamount;
    }

    public void setProductamount(int productamount) {
        this.productamount = productamount;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    public Double getProductpricetotal() {
        return productpricetotal;
    }

    public void setProductpricetotal(Double productpricetotal) {
        this.productpricetotal = productpricetotal;
    }

    public Double getProductcost() {
        return productcost;
    }

    public void setProductcost(Double productcost) {
        this.productcost = productcost;
    }

    public Double getProductcosttotal() {
        return productcosttotal;
    }

    public void setProductcosttotal(Double productcosttotal) {
        this.productcosttotal = productcosttotal;
    }
}
