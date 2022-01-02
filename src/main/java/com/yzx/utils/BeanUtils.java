package com.yzx.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtils {
    public static <T> T ParamsToBean(Map map, T t) {
        
        try {
            org.apache.commons.beanutils.BeanUtils.populate(t,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
}
