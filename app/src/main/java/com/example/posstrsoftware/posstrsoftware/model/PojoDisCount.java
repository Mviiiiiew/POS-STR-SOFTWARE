package com.example.posstrsoftware.posstrsoftware.model;

/**
 * Created by Wasabi on 11/10/2016.
 */

public class PojoDisCount {
    String discountcost;
    String discountpercent;

    public String getDiscountcost() {
        return discountcost;
    }

    public void setDiscountcost(String discountcost) {
        this.discountcost = discountcost;
    }

    public String getDiscountpercent() {
        return discountpercent;
    }

    public void setDiscountpercent(String discountpercent) {
        this.discountpercent = discountpercent;
    }

    @Override
    public String toString() {
        return "PojoDisCount{" +
                "discountcost='" + discountcost + '\'' +
                ", discountpercent='" + discountpercent + '\'' +
                '}';
    }
}
