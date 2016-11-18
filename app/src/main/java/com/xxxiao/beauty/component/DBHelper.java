package com.xxxiao.beauty.component;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xxxiao.beauty.model.AlbumDao;
import com.xxxiao.beauty.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Growth on 2016/3/3.
 */
public class DBHelper extends DaoMaster.OpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 升级数据库，在主线程打开数据库就是在主线程升级，在子线程打开数据库就在子线程升级，不要阻塞这个方法
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Logger.e("Upgrading schema from version " + oldVersion + " to " + newVersion);
        MigrationHelper.migrate(db, AlbumDao.class);
    }

}
