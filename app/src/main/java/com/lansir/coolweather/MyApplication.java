package com.lansir.coolweather;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePalApplication.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
