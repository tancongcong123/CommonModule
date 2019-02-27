package com.jx.commonlibrary.log;

import android.util.Log;

import com.jx.commonlibrary.BuildConfig;

/*

 */
public class CustomLog {

    public static void d(String tag, String msg){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg);
        }
    }
    public static void d(String tag, String msg, Exception throwable){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg, throwable);
        }
    }

    public static void i(String tag, String msg){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg);
        }
    }
    public static void i(String tag, String msg, Exception throwable){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg, throwable);
        }
    }

    public static void e(String tag, String msg){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg, Exception throwable){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg, throwable);
        }
    }
}
