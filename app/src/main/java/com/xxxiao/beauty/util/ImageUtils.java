package com.xxxiao.beauty.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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

}
