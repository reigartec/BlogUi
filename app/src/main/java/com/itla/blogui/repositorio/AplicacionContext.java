package com.itla.blogui.repositorio;

import android.app.Application;
import android.content.Context;

public class AplicacionContext extends Application {
    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
