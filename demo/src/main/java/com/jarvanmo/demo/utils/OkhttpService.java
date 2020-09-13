package com.jarvanmo.demo.utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yanglin on 18-4-10.
 */

public class OkhttpService {

    public interface OnResponseListener{
        void onSuccess(String result);
        void onFailure(IOException error);
    }

//    Cache cache = new Cache();
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String TAG = "1";
    public static String basePath = "http://192.168.1.1";

    protected String doGet(OkHttpClient okHttpClient, String url, final OnResponseListener listener) {
        url = basePath+url;
        Request request = new Request.Builder()
                //.header("authorization", cache.getToken())
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int index1 = result.indexOf("调用失败");
                int index2 = result.indexOf("JsonWebToken");
                if(index1 != -1 || index2 != -1) {
                    //cache.refreshToken(cache.getUser());
                    return;
                }
                listener.onSuccess(result);
            }
        });
        return null;
    }
}