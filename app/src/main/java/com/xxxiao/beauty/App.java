package com.xxxiao.beauty;

import android.app.Application;

import com.xxxiao.beauty.component.DBManager;

/**
 * Created by Administrator on 2016/11/16.
 */

public class App extends Application {

    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        DBManager.getInstance().getSession();
    }

    public static App getContext() {
        return sContext;
    }

}
