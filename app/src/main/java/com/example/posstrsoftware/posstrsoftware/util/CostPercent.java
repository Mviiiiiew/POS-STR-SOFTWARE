package com.example.posstrsoftware.posstrsoftware.util;

/**
 * Created by Wasabi on 11/17/2016.
 */

public class CostPercent {

    public static double costpercent(Double x, Double y) {
        double u = 0.0;
        double z = (x * y) / 100.0;
        int i = (int) (z * 1000);
        ////Decimal////
        int d = i % 1000;


        if (d >= 0 && d < 250) {
            double p = 0.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;


        } else if (d >= 250 && d < 500) {
            double p = 250.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;


        } else if (d >= 500 && d < 750) {
            double p = 500.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;



        } else if (d >= 750 && d<1000) {
            double p = 750.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;

        }   else if(d<=1000) {
            double p = 1000.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;
        }

        return u;
    }
}
