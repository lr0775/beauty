package com.xxxiao.beauty.util;

/**
 * Created by Administrator on 2016/11/16.
 */

public class StringUtils {

    public static String getOriginalImageURL(String url) {
        int index = url.lastIndexOf("-");
        int dotIndex = url.lastIndexOf(".");
        String originalURL = url.substring(0, index) + url.substring(dotIndex, url.length());
        return originalURL;
    }

}
