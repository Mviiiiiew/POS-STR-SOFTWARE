package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by Wasabi on 11/28/2016.
 */

public class ReportList {

private int sale_masterid ;
    private  String date;
    private  double discount;
    private  int amount;
    private  double sumCost;
    private  double sumPrice;
    private  double Profit;
    private  double sumVAT;
    private  String BillId;

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public double getSumCost() {
        return sumCost;
    }

    public void setSumCost(double sumCost) {
        this.sumCost = sumCost;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public double getProfit() {
        return Profit;
    }

    public void setProfit(double profit) {
        Profit = profit;
    }

    public double getSumVAT() {
        return sumVAT;
    }

    public void setSumVAT(double sumVAT) {
        this.sumVAT = sumVAT;
    }

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
