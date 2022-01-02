package com.yzx.utils;

import org.junit.Test;

public class WebUtils {
    public static int parseInt(String obj, Integer defaultvalue) {
        if(obj == null){
            return defaultvalue;
        }else return Integer.parseInt(obj);
    }

}



