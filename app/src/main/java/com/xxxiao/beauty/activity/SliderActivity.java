package com.xxxiao.beauty.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.component.Logger;
import com.xxxiao.beauty.constant.KEY;
import com.xxxiao.beauty.model.Photo;
import com.xxxiao.beauty.util.ImageUtils;
import com.xxxiao.beauty.view.Toaster;

import java.util.ArrayList;

public class SliderActivity extends BaseActivity {

    private RelativeLayout mContainerLayout;
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
        mContainerLayout = (RelativeLayout) findViewById(R.id.container);

        mContainerLayout.setOnClickListener(new View.OnClickListener() {
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
                mContainerLayout.post(mTask);
            }

            @Override
            public void onLoadDone(final Bitmap bitmap) {
                final ImageView iv = new ImageView(mActivity);
                //iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                mContainerLayout.addView(iv, -1, params);
                iv.setImageBitmap(bitmap);
                AlphaAnimation fadeImage = new AlphaAnimation(0, 1);
                fadeImage.setDuration(500);
                fadeImage.setInterpolator(new DecelerateInterpolator());
                fadeImage.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mContainerLayout.postDelayed(mTask, 2500);
                        if (mContainerLayout.getChildCount() > 1) {
                            mContainerLayout.removeViewAt(0);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                iv.startAnimation(fadeImage);
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
        mContainerLayout.post(mTask);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mContainerLayout.removeCallbacks(mTask);
    }

}
