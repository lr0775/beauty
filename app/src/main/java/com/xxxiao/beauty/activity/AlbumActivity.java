package com.xxxiao.beauty.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.adapter.PhotoAdapter;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.component.TaskCallback;
import com.xxxiao.beauty.component.TaskError;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Photo;
import com.xxxiao.beauty.task.SpiderTask;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

public class AlbumActivity extends BaseActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;
    private ArrayList<Photo> mList;

    private String mLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        mLink = getIntent().getStringExtra(KEY.LINK);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mList = new ArrayList<>();
        mAdapter = new PhotoAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setInterface(new PhotoAdapter.AdapterInterface() {
            @Override
            public void onItemClick(int position, Photo item) {

            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                crawlPhotoList();
            }
        });

        mRefreshLayout.setRefreshing(true);
        crawlPhotoList();
    }

    private void crawlPhotoList() {
        mTaskManager.start(SpiderTask.crawlPhotoList(mLink)
                .setCallback(new TaskCallback<ArrayList<Photo>>() {

                    @Override
                    public void onFinish() {
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(TaskError e) {
                        Toaster.show(e.msg);
                    }

                    @Override
                    public void onSuccess(ArrayList<Photo> result) {
                        mList.clear();
                        mList.addAll(result);
                        mAdapter.notifyDataSetChanged();
                    }
                }));
    }

}
