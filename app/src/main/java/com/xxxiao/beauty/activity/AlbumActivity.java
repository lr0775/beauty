package com.xxxiao.beauty.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.adapter.PhotoAdapter;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.component.TaskCallback;
import com.xxxiao.beauty.component.TaskError;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Album;
import com.xxxiao.beauty.model.Photo;
import com.xxxiao.beauty.task.SpiderTask;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

public class AlbumActivity extends BaseActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;
    private ArrayList<Photo> mList;

    private Album mAlbum;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        mAlbum = getIntent().getParcelableExtra(KEY.ALBUM);
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
                Intent intent = new Intent(mActivity, SliderActivity.class);
                intent.putExtra(KEY.PHOTO_LIST, mList);
                intent.putExtra(KEY.POSITION, position);
                startActivity(intent);
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
        mTaskManager.start(SpiderTask.crawlPhotoList(mAlbum.link)
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

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        showConfirmDialog();
        return super.onMenuOpened(featureId, menu);
    }

    private void showConfirmDialog() {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(mActivity)
                    .setTitle("确认收藏吗？")
                    .setMessage(mAlbum.name)
                    .setNeutralButton("再看看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create();
            mDialog.show();
            return;
        }
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        } else {
            mDialog.show();
        }
    }
}
