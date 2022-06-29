package com.china.rescue.common;

public class CustStringUtils {

    private final static String NULLSTR = "";

    private static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str);
    }

    public static boolean isEmpty(Object[] objects){
        return isNull(objects) || objects.length == 0;
    }

    public static boolean isNull(Object object){
        return object == null;
    }

    public static boolean isNotNull(Object object){
        return !isNull(object);
    }

}
