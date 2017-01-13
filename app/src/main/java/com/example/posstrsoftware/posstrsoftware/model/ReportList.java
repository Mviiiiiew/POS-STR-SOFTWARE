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
    private  String NameProduct;
    private  String NameUnit;
    private  Double SaleAmt;
    private  Double SumSaleAmt;
    private  String PrintProductDate;

    private int xSumAmount ;
    private  Double xSumSaleAmt;
    private  Double xSumCost;
    private  Double xSumVat;
    private  Double xSumDisc;
    private  Double xSumProfit;


    public String getPrintProductDate() {
        return PrintProductDate;
    }

    public void setPrintProductDate(String printProductDate) {
        PrintProductDate = printProductDate;
    }

    private  int ProductprintAmount;

    public int getProductprintAmount() {
        return ProductprintAmount;
    }

    public void setProductprintAmount(int productprintAmount) {
        ProductprintAmount = productprintAmount;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getNameUnit() {
        return NameUnit;
    }

    public void setNameUnit(String nameUnit) {
        NameUnit = nameUnit;
    }

    public Double getSaleAmt() {
        return SaleAmt;
    }

    public void setSaleAmt(Double saleAmt) {
        SaleAmt = saleAmt;
    }

    public Double getSumSaleAmt() {
        return SumSaleAmt;
    }

    public void setSumSaleAmt(Double sumSaleAmt) {
        SumSaleAmt = sumSaleAmt;
    }

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

    public int getxSumAmount() {
        return xSumAmount;
    }

    public void setxSumAmount(int xSumAmount) {
        this.xSumAmount = xSumAmount;
    }

    public Double getxSumSaleAmt() {
        return xSumSaleAmt;
    }

    public void setxSumSaleAmt(Double xSumSaleAmt) {
        this.xSumSaleAmt = xSumSaleAmt;
    }

    public Double getxSumCost() {
        return xSumCost;
    }

    public void setxSumCost(Double xSumCost) {
        this.xSumCost = xSumCost;
    }

    public Double getxSumVat() {
        return xSumVat;
    }

    public void setxSumVat(Double xSumVat) {
        this.xSumVat = xSumVat;
    }

    public Double getxSumDisc() {
        return xSumDisc;
    }

    public void setxSumDisc(Double xSumDisc) {
        this.xSumDisc = xSumDisc;
    }

    public Double getxSumProfit() {
        return xSumProfit;
    }

    public void setxSumProfit(Double xSumProfit) {
        this.xSumProfit = xSumProfit;
    }
}
