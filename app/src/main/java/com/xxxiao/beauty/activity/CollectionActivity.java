package com.xxxiao.beauty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.adapter.AlbumAdapter;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.component.TaskCallback;
import com.xxxiao.beauty.component.TaskError;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Album;
import com.xxxiao.beauty.task.CollectionTask;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

public class CollectionActivity extends BaseActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private AlbumAdapter mAdapter;
    private ArrayList<Album> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mList = new ArrayList<>();
        mAdapter = new AlbumAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setInterface(new AlbumAdapter.AdapterInterface() {
            @Override
            public void onItemClick(int position, Album item) {
                Intent intent = new Intent(mActivity, AlbumActivity.class);
                intent.putExtra(KEY.ALBUM, item);
                startActivity(intent);
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                crawlAlbumList();
            }
        });

        mRefreshLayout.setRefreshing(true);
        crawlAlbumList();
    }

    private void crawlAlbumList() {
        mTaskManager.start(CollectionTask.getAlbumList()
                .setCallback(new TaskCallback<ArrayList<Album>>() {

                    @Override
                    public void onFinish() {
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(TaskError e) {
                        Toaster.show(e.msg);
                    }

                    @Override
                    public void onSuccess(ArrayList<Album> result) {
                        mList.clear();
                        mList.addAll(result);
                        mAdapter.notifyDataSetChanged();
                    }
                }));
    }
}
