package com.theonetech.android.domain.utils;

import android.app.Application;
import android.util.Log;

import com.theonetech.android.BuildConfig;


/**
 * Created by bhoomika prajapati on 8/6/20.
 */
public class Logger {

    private static String TAG = Application.class.getName();

    public static void setTAG(String TAG) {
        Logger.TAG = TAG;
    }

    private static boolean isDebugMode() {
        return BuildConfig.DEBUG;
    }


    public static void w(String message) {
        if (isDebugMode()) {
            Log.w(Logger.TAG, message);
        }
    }

    public static void i(String message) {
        if (isDebugMode()) {
            Log.i(Logger.TAG, message);
        }
    }

    public static void e(String message) {
        if (isDebugMode()) {
            Log.e(Logger.TAG, message);
        }
    }

    public static void v(String message) {
        if (isDebugMode())
            Log.v(Logger.TAG, message);
    }


    public static void d(String message) {
        if (isDebugMode())
            Log.d(Logger.TAG, message);
    }
}
