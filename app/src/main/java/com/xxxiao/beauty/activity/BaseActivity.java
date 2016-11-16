package com.xxxiao.beauty.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xxxiao.beauty.component.TaskManager;

/**
 * Created by Administrator on 2016/10/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected BaseActivity mActivity;
    protected TaskManager mTaskManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mTaskManager = new TaskManager();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTaskManager.onDestroy();
    }
}
