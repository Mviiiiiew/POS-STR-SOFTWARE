package com.example.posstrsoftware.posstrsoftware.util;

import java.util.StringTokenizer;

/**
 * Created by Wasabi on 11/17/2016.
 */

public class VAT {

    public static double VATRATE(Double x, Double y) {
        double u = 0.0;
        double z = (x * y) / 100.0;
        int i = (int) (z * 1000);
        ////Decimal////
        int d = i % 1000;
        if(d<=100){
            double p = 0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;

        }else if (d >= 101 && d <= 250) {
            double p = 250.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;


        } else if (d >= 251 && d <= 500) {
            double p = 500.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;


        } else if (d >= 501 && d <= 750) {
            double p = 750.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;



        } else if (d >= 751) {
            double p = 1000.0;
            double r = p / 1000.0;
            int o = (int) z;
            u = o + r;

        }


        return u;
    }
}
