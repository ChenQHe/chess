package com.flyvr.chess;

import android.app.Application;

/**
 * Created by android studio
 * author:chen
 */
public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
