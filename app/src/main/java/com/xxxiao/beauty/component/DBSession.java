package com.xxxiao.beauty.component;

import com.xxxiao.beauty.App;
import com.xxxiao.beauty.model.DaoMaster;
import com.xxxiao.beauty.model.DaoSession;

/**
 * Created by Administrator on 2016/11/17.
 */

public class DBSession {

    private static final String DB_NAME = "app.db";

    private static volatile DBSession sInstance;

    public static DBSession getInstance() {
        if (sInstance == null) {
            synchronized (DBSession.class) {
                if (sInstance == null) {
                    sInstance = new DBSession();
                }
            }
        }
        return sInstance;
    }

    private DaoSession mSession;

    private DBSession() {
        MyDbOpenHelper helper = new MyDbOpenHelper(App.getContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        mSession = daoMaster.newSession();
    }

    public DaoSession getSession() {
        return mSession;
    }

}
