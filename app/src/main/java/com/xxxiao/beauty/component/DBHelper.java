package com.xxxiao.beauty.component;

import com.xxxiao.beauty.App;
import com.xxxiao.beauty.model.DaoMaster;
import com.xxxiao.beauty.model.DaoSession;

/**
 * Created by Administrator on 2016/11/17.
 */

public class DBHelper {

    private static final String DB_NAME = "app.db";

    private static volatile DBHelper sInstance;

    public static DBHelper getInstance() {
        if (sInstance == null) {
            synchronized (DBHelper.class) {
                if (sInstance == null) {
                    sInstance = new DBHelper();
                }
            }
        }
        return sInstance;
    }

    private DaoSession mSession;

    private DBHelper() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mSession = daoMaster.newSession();
    }

    public DaoSession getSession() {
        return mSession;
    }

}
