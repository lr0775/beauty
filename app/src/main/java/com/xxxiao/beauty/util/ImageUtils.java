package com.xxxiao.beauty.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xxxiao.beauty.App;

import java.io.File;

/**
 * Created by Administrator on 2016/10/17.
 */

public class ImageUtils {

    public static void load(String url, ImageView view) {
        Glide.with(App.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    public static void load(File file, ImageView view) {
        Glide.with(App.getContext())
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }

    public static void load(String url, final OnLoadListener listener) {
        Glide.with(App.getContext())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        if (listener != null) {
                            listener.onLoadFailed(e);
                        }
                    }

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (listener != null) {
                            listener.onLoadDone(resource);
                        }
                    }
                });
    }

    public interface OnLoadListener {

        void onLoadFailed(Exception e);

        void onLoadDone(Bitmap bitmap);
    }

}
