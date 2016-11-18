package com.xxxiao.beauty.component;

import com.xxxiao.beauty.App;
import com.xxxiao.beauty.model.DaoMaster;
import com.xxxiao.beauty.model.DaoSession;

/**
 * Created by Administrator on 2016/11/17.
 */

public class DBManager {

    private static final String DB_NAME = "app.db";

    private static volatile DBManager sInstance;

    public static DBManager getInstance() {
        if (sInstance == null) {
            synchronized (DBManager.class) {
                if (sInstance == null) {
                    sInstance = new DBManager();
                }
            }
        }
        return sInstance;
    }

    private DaoSession mSession;

    private DBManager() {
        DBHelper helper = new DBHelper(App.getContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        mSession = daoMaster.newSession();
    }

    public DaoSession getSession() {
        return mSession;
    }

}
