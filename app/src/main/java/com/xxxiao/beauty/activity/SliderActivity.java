package com.xxxiao.beauty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Photo;
import com.xxxiao.beauty.util.ImageUtils;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

public class SliderActivity extends AppCompatActivity {

    private ImageView mSliderIv;
    private ArrayList<Photo> mPhotoList;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        mPhotoList = getIntent().getParcelableArrayListExtra(KEY.PHOTO_LIST);
        if (mPhotoList == null) {
            finish();
            return;
        }
        mPosition = getIntent().getIntExtra(KEY.POSITION, 0);
        mSliderIv = (ImageView) findViewById(R.id.slider);

        loadImage(mPhotoList.get(mPosition).url);
    }

    private void loadImage(String url) {
        ImageUtils.load(url, mSliderIv, new ImageUtils.OnLoadListener() {
            @Override
            public void onLoadFailed(Exception e) {
                Toaster.show(e.getMessage());
            }

            @Override
            public void onLoadDone() {
                mSliderIv.postDelayed(mTask, 2500);
            }
        });
    }

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            int p = mPosition + 1;
            if (p >= mPhotoList.size()) {
                mPosition = 0;
            } else {
                mPosition = p;
            }
            loadImage(mPhotoList.get(mPosition).url);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSliderIv.removeCallbacks(mTask);
    }
}
