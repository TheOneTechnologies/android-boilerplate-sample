package com.theonetech.android.domain.application;

import android.app.Application;
import android.content.Context;


public class GlobalApplication extends Application {

    /**
     * Application
     * */

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }




    public static Context getAppContext() {
        return mContext;
    }
}
