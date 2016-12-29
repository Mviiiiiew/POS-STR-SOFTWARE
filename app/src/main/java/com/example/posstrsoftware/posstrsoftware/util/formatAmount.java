package com.example.posstrsoftware.posstrsoftware.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Wasabi on 11/3/2016.
 */

public class formatAmount {
    public static String formatAmountDouble(Double num)
    {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(Double.valueOf(num));
    }
    public static String formatAmountDouble(int num)
    {
        DecimalFormat df = new DecimalFormat("##");
        return df.format(num);
    }

    public static String DecimalFormatDouble(Double num)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(Double.valueOf(num));
    }


}
