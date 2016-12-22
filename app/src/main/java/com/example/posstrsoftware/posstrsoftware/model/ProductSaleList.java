package com.example.posstrsoftware.posstrsoftware.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.BaseAdapter;


/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleList {
    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    private String dateId;
    private int id;
    private int productid;
    private int unitid;
    private int groupid;
    private int saleMasterid;
    private String groupname;
    private Double product_price;
    private Double product_cost;
    private String vat_flag;
    private Double vatrate;
    private Double vat;
    private String afterdate;
    private String RunIdBill;
    private String BillId;
    private String SymbolVat;
    private Double ValueVat;

    public Double getValueVat() {
        return ValueVat;
    }

    public void setValueVat(Double valueVat) {
        ValueVat = valueVat;
    }

    public String getSymbolVat() {
        return SymbolVat;
    }

    public void setSymbolVat(String symbolVat) {
        SymbolVat = symbolVat;
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getRunIdBill() {
        return RunIdBill;
    }

    public void setRunIdBill(String runIdBill) {
        RunIdBill = runIdBill;
    }

    public String getAfterdate() {
        return afterdate;
    }

    public void setAfterdate(String afterdate) {
        this.afterdate = afterdate;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getVatrate() {
        return vatrate;
    }

    public void setVatrate(Double vatrate) {
        this.vatrate = vatrate;
    }

    public String getVat_flag() {
        return vat_flag;
    }

    public void setVat_flag(String vat_flag) {
        this.vat_flag = vat_flag;
    }

    public Double getProduct_price() {

        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Double getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(Double product_cost) {
        this.product_cost = product_cost;
    }

    public int getSaleMasterid() {
        return saleMasterid;
    }

    public void setSaleMasterid(int saleMasterid) {
        this.saleMasterid = saleMasterid;
    }

    private String discount;
    private String unitname;

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    private String amount;
    private String ProductSale;
    private Double price;
    boolean checkbox;


    public ProductSaleList() {

    }

    public ProductSaleList(int id, String productSale, Double price) {
        this.id = id;
        this.price = price;
        ProductSale = productSale;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
