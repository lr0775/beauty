package com.xxxiao.beauty.component;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;

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

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Logger.e("Upgrading schema from version " + oldVersion + " to " + newVersion);
        Logger.e("onUpgrade in main thread ? " + (Looper.myLooper() == Looper.getMainLooper())); //false, not in main thread
        MigrationHelper.migrate(db, AlbumDao.class);
    }

}
