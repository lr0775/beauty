package com.xxxiao.beauty.util;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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

    public static void load(String url, ImageView view, final OnLoadListener listener) {
        Glide.with(App.getContext())
                .load(url)
                .animate(new ViewPropertyAnimation.Animator() {
                    @Override
                    public void animate(View view) {
                        view.setAlpha(0f);
                        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                        fadeAnim.setDuration(100);
                        fadeAnim.start();
                    }
                })
                .into(new GlideDrawableImageViewTarget(view) {

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (listener != null) {
                            listener.onLoadFailed(e);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (listener != null) {
                            listener.onLoadDone();
                        }
                    }
                });
    }

    public interface OnLoadListener {

        void onLoadFailed(Exception e);

        void onLoadDone();
    }

}
