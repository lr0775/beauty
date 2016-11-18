package com.xxxiao.beauty.component;

import com.alibaba.fastjson.JSONObject;
import com.xxxiao.beauty.constant.API;
import com.xxxiao.beauty.util.NetUtils;

import java.io.IOException;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHelper {

    private static final String TAG = "OkHttpHelper";

    public static final MediaType TEXT = MediaType.parse("text/html; charset=utf-8");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType IMAGE = MediaType.parse("image/*");
    public static final MediaType STREAM = MediaType.parse("application/octet-stream");

    private static volatile OkHttpHelper sInstance;

    public static OkHttpHelper getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpHelper();
                }
            }
        }
        return sInstance;
    }

    private OkHttpClient mClient;

    private OkHttpHelper() {
        mClient = new OkHttpClient();
    }

    public HttpResponse get(String url) throws IOException {
        if (!NetUtils.isNetworkAvailable()) {
            return new HttpResponse(new TaskError("网络不可用"), "");
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = mClient.newCall(request).execute();
        String res = response.body().string();
        Logger.e("url = " + url + ", response = " + res);
        TaskError error = null;
        String result = res;
        return new HttpResponse(error, result);
    }

    public Response getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return mClient.newCall(request).execute();
    }

    public HttpResponse post(String method, JSONObject o) throws IOException {
        if (!NetUtils.isNetworkAvailable()) {
            return new HttpResponse(new TaskError("网络不可用"), "");
        }
        String url = API.HOST + method;
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keySet = o.keySet();
        for (String key : keySet) {
            builder.add(key, o.get(key).toString());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = mClient.newCall(request).execute();
        String res = response.body().string();
        Logger.e("url = " + url + ", response = " + res);

        TaskError error = null;
        String result = res;
        return new HttpResponse(error, result);
    }

}
