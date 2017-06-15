package com.guoxiaotian.lifesecretary.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/2/23.
 */
public class CacheUtils {

    private static final String SP_NAME="xun_xi_xiao_mi";

    //private static SharedPreferences sharedPreferences;


    //数据保存到Sp中
    public static void putString(Context context, String key, String values) {

        SharedPreferences sharedPreferences=context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);

        sharedPreferences.edit().putString(key,values).commit();
    }

    //根据key取出缓存数据
    public static String getString(Context context, String newLaughTextUrl) {

        SharedPreferences sharedPreferences=context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);

        String CacheJson = sharedPreferences.getString(newLaughTextUrl, "");
        return CacheJson;
    }
}
