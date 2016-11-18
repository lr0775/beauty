package com.xxxiao.beauty.util;

/**
 * Created by Administrator on 2016/11/16.
 */

public class StringUtils {

    public static String getOriginalImageURL(String url) {
        String originalURL = url;
        int dotIndex = url.lastIndexOf(".");
        int start = dotIndex - 11;
        if (start < 0) {
            return originalURL;
        }
        String suffix = url.substring(start, dotIndex);
        if (suffix.contains("-") && suffix.contains("x")) {
            int index = url.lastIndexOf("-");
            originalURL = url.substring(0, index) + url.substring(dotIndex, url.length());
        }
        return originalURL;
    }

    public static Long getAlbumID(String link) {
        int index = link.lastIndexOf("/");
        String idStr = link.substring(index + 1, link.length());
        Long id = 0L;
        try {
            id = Long.valueOf(idStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return id;
    }

}
