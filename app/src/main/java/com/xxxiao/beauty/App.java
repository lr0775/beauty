package com.xxxiao.beauty;

import android.app.Application;

import com.xxxiao.beauty.component.DBManager;
import com.xxxiao.beauty.component.ThreadPool;

/**
 * Created by Administrator on 2016/11/16.
 */

public class App extends Application {

    private static App sContext;

    public static App getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        initDB();
    }

    private void initDB() {
        ThreadPool.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                DBManager.getInstance().getSession();
            }
        });
    }

}
