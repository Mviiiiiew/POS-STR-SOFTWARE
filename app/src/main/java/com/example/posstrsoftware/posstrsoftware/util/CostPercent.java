package com.example.posstrsoftware.posstrsoftware.util;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by Wasabi on 11/17/2016.
 */

public class CostPercent {

    public static double costpercent(Double x, Double y) {
        return y == 100.0?x: Double.parseDouble(new DecimalFormat("#0.00").format((x * (y / 100))));

    }
    public static double parserFormat(Double input){
        String[] cal = new DecimalFormat("#0.00").format(input).split("\\.");
        Log.d("input=",cal[0]+"");

        int decimal1 = Integer.parseInt(cal[1]);
         int x = Integer.parseInt(cal[0]);
        int y = 1;
        String calx = String.valueOf(x+y);
        Log.d("inputx=",x+"");
        return Double.parseDouble(isCheck(1,25,decimal1)?cal[0]+".25":isCheck(26,50,decimal1)?cal[0]+".50":isCheck(51,75,decimal1)?cal[0]+".75":isCheck(76,99,decimal1)?calx:".00");
    }
    private static boolean isCheck(int input_from,int input_to,int input_value){
        return input_value >= input_from && input_value <= input_to;
    }
}
