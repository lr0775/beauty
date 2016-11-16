package com.xxxiao.beauty.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.adapter.AlbumAdapter;
import com.xxxiao.beauty.component.TaskCallback;
import com.xxxiao.beauty.component.TaskError;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Album;
import com.xxxiao.beauty.task.SpiderTask;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/16.
 */

public class MainFragment extends BaseFragment {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private AlbumAdapter mAdapter;
    private ArrayList<Album> mList;

    private String mCategory;
    private int mPage = 1;

    public MainFragment() {
    }

    public static MainFragment newInstance(String category) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(KEY.CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCategory = getArguments().getString(KEY.CATEGORY);

        mRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mList = new ArrayList<>();
        mAdapter = new AlbumAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setInterface(new AlbumAdapter.AdapterInterface() {
            @Override
            public void onItemClick(int position, Album item) {

            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                crawlAlbumList();
            }
        });

        mRefreshLayout.setRefreshing(true);
        crawlAlbumList();
    }

    private void crawlAlbumList() {
        mTaskManager.start(SpiderTask.crawlAlbumList(mCategory, mPage)
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
                        if (mPage == 1) {
                            mList.clear();
                        }
                        mList.addAll(result);
                        mAdapter.notifyDataSetChanged();
                    }
                }));
    }


}
