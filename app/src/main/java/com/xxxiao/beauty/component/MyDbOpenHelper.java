package com.xxxiao.beauty.component;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xxxiao.beauty.model.AlbumDao;
import com.xxxiao.beauty.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Growth on 2016/3/3.
 */
public class MyDbOpenHelper extends DaoMaster.OpenHelper {
    public MyDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        MigrationHelper.migrate(db, AlbumDao.class);
    }

}
