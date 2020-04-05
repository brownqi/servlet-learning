package cn.brownqi.utils;


public class ObjectUtils {

    private static Object emptyObject = new Object();

    public static  <T> T emptyObject(){
        return (T) emptyObject;
    }
}
