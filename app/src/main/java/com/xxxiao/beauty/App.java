package com.xxxiao.beauty;

import android.app.Application;

/**
 * Created by Administrator on 2016/11/16.
 */

public class App extends Application {

    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static App getContext() {
        return sContext;
    }

}
