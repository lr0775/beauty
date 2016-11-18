package com.xxxiao.beauty.task;

import com.xxxiao.beauty.component.DBSession;
import com.xxxiao.beauty.component.Task;
import com.xxxiao.beauty.model.Album;
import com.xxxiao.beauty.model.AlbumDao;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18.
 */

public class CollectionTask {

    public static Task<ArrayList<Album>> getAlbumList() {
        return new Task<ArrayList<Album>>() {
            @Override
            protected void call() {
                AlbumDao dao = DBSession.getInstance().getSession().getAlbumDao();
                ArrayList<Album> dataList = (ArrayList<Album>) dao.queryBuilder().build().list();
                onSuccess(dataList);
            }
        };
    }


}
