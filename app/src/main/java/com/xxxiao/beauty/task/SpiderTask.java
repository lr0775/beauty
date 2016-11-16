package com.xxxiao.beauty.task;

import com.xxxiao.beauty.component.Task;
import com.xxxiao.beauty.constant.API;
import com.xxxiao.beauty.model.Album;
import com.xxxiao.beauty.model.Photo;
import com.xxxiao.beauty.util.StringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/16.
 */

public class SpiderTask {

    public static Task<ArrayList<Album>> crawlAlbumList(final String category, final int page) {
        return new Task<ArrayList<Album>>() {
            @Override
            protected void call() {
                ArrayList<Album> dataList = new ArrayList<>();
                try {
                    Document document = Jsoup.connect(API.HOST + category + "/page/" + page).get();
                    Element imageListelement = document.getElementById("blog-grid");
                    Elements imageListElements = imageListelement.getElementsByAttributeValueContaining("class", "col-lg-4 col-md-4 three-columns post-box");
                    for (Element imageListElement : imageListElements) {
                        Element a = imageListElement.select("a[href]").first();
                        String link = a.attr("abs:href");

                        Element img = imageListElement.select("img").first();
                        String cover = img.attr("abs:src");
                        cover = StringUtils.getOriginalImageURL(cover);
                        String name = img.attr("alt").trim();
                        Album album = new Album();
                        album.name = name;
                        album.link = link;
                        album.cover = cover;
                        dataList.add(album);
                    }
                    onSuccess(dataList);
                } catch (IOException e) {
                    e.printStackTrace();
                    onError(e);
                }
            }
        };
    }

    public static Task<ArrayList<Photo>> crawlPhotoList(final String link) {
        return new Task<ArrayList<Photo>>() {
            @Override
            protected void call() {
                ArrayList<Photo> dataList = new ArrayList<>();
                try {
                    Document document = Jsoup.connect(link).get();
                    Element element = document.getElementById("rgg-imagegrid gallery");
                    Elements elementsA = element.getElementsByTag("a");
                    for (Element a : elementsA) {
                        String url = a.attr("abs:href");
                        url = StringUtils.getOriginalImageURL(url);
                        Photo photo = new Photo();
                        photo.url = url;
                        dataList.add(photo);
                    }
                    onSuccess(dataList);
                } catch (IOException e) {
                    e.printStackTrace();
                    onError(e);
                }
            }
        };
    }

}
