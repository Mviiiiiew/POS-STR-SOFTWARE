package com.example.posstrsoftware.posstrsoftware.util;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by Wasabi on 11/17/2016.
 */

public class CostPercent {

    public static double costpercent(Double x, Double y) {
        return y== 100.0?x: Double.parseDouble(new DecimalFormat("##.00").format((x * (y / 100))));

    }
    public static double parserFormat(Double input){
        String[] cal = new DecimalFormat("##.00").format(input).split("\\.");
        Log.d("input=",cal[1]+"");
        int decimal1 = Integer.parseInt(cal[1]);
        return Double.parseDouble(cal[0] += isCheck(1,25,decimal1)?".25":isCheck(26,50,decimal1)?".50":isCheck(51,75,decimal1)?".75":".00");
    }
    private static boolean isCheck(int input_from,int input_to,int input_value){
        return input_value >= input_from && input_value <= input_to;
    }
}
