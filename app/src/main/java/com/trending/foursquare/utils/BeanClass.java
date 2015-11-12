package com.trending.foursquare.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Haimal on 11/10/2015.
 */
public class BeanClass implements java.io.Serializable {

    private static String[] name;
    private static String searchTerm;
    private static int flag;

    private  BeanClass(){}
    private static BeanClass beanClass = new BeanClass();
    public static BeanClass getInstance()
    {
        return beanClass;
    }
    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        BeanClass.flag = flag;
    }

    public static String getSearchTerm() {

       if(searchTerm != null)
        Log.d("BeanText", searchTerm);
        return searchTerm;
    }

    public static void setSearchTerm(String searchTerm) {
        BeanClass.searchTerm = searchTerm;
    }


}
