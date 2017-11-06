package com.itcast.yb.packelves.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by taotao on 2017/11/6
 * sharpreference工具类
 */
public class PreTool {

    /**
     * 获取配置参数 String
     *
     * @return
     */
    public static String getStringPreference(Context context,String name,String defvalues){
        SharedPreferences preferences=context.getSharedPreferences(name,Context.MODE_PRIVATE);
        synchronized (preferences){
         return preferences.getString(name,defvalues);
        }
    }

    /*
	 * String 设置配置参数
	 */
    public static void  setStringsave(Context context,String name,String values){
        SharedPreferences preferences=context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(name,values);
        editor.commit();
    }

    /*
         * int,获取配置参数
         */
    public static int getIntPreferences(Context context, String name,
                                        int defValues) {
        SharedPreferences preferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        synchronized (preferences) {
            return preferences.getInt(name, defValues);
        }
    }

    /*
     * int,设置参数
     */
    public static void setIntSave(Context context, String name, int value) {
        SharedPreferences preferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        synchronized (preferences) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(name, value);
            editor.commit();
        }
    }

    /*
     * boolean类型,获取配置参数
     */
    public static boolean getBooleanPreferences(Context context, String name,
                                                boolean defValues) {
        SharedPreferences preferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        synchronized (preferences) {
            return preferences.getBoolean(name, defValues);
        }
    }

    /*
     * boolean类型,设置参数
     */
    public static void setBooleanSave(Context context, String name,
                                      boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        synchronized (preferences) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(name, value);
            editor.commit();
        }
    }





}
