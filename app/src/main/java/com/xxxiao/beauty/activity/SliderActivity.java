package com.xxxiao.beauty.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.component.Logger;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Photo;
import com.xxxiao.beauty.util.ImageUtils;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

public class SliderActivity extends BaseActivity {

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

        mSliderIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, EmptyActivity.class));
            }
        });
    }

    private void loadImage(String url) {
        ImageUtils.load(url, new ImageUtils.OnLoadListener() {
            @Override
            public void onLoadFailed(Exception e) {
                Toaster.show(e.getMessage());
            }

            @Override
            public void onLoadDone(Bitmap bitmap) {
                mSliderIv.setImageBitmap(bitmap);
                mSliderIv.postDelayed(mTask, 2500);
            }
        });
    }

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            Logger.e("position= " + mPosition);
            loadImage(mPhotoList.get(mPosition).url);
            int p = mPosition + 1;
            if (p >= mPhotoList.size()) {
                mPosition = 0;
            } else {
                mPosition = p;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSliderIv.post(mTask);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSliderIv.removeCallbacks(mTask);
    }

}
