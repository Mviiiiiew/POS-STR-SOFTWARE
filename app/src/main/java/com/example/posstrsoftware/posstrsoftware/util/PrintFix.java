package com.example.posstrsoftware.posstrsoftware.util;

/**
<<<<<<< HEAD
 * Created by Wasabi on 11/22/2016.
=======
 * Created by MAN on 11/21/2016.
>>>>>>> origin/master
 */

public class PrintFix {
    public static  String generateName(String input,int intSprit){
        return input.length() > intSprit ? input.substring(0,intSprit):formatStringName(input,intSprit);
    }
    private static String formatStringName(String input,int intSprit){
        int i = input.length();
        while(i<intSprit){
            input += " ";
            i++;
        }
        return input;
    }
    public static  String generatePrice(String input,int intSprit){
        return input.length() > intSprit ? input.substring(0,intSprit):formatStringPrice(input,intSprit);
    }
    private static String formatStringPrice(String input,int intSprit){
        int i = input.length();
        while(i<intSprit){
            input = " "+input;
            i++;
        }
        return input;
    }

}
