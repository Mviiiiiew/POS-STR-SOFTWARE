package com.example.posstrsoftware.posstrsoftware.util;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class Util_String {
    public static String getGennerlateString(String input_value){
        return  input_value.trim().replaceAll("","").replaceAll("'|\"","");
    }
}
