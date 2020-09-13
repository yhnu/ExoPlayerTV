package com.jarvanmo.tttv.utils;


import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yanglin on 18-4-10.
 */

public class OkhttpService {

    public interface OnResponseListener{
        void onSuccess(String result);
        void onFailure(IOException error);
    }

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String TAG = "1";

    protected String doGet(OkHttpClient okHttpClient, String url, final OnResponseListener listener) {
        Log.i(TAG, "doGet: " + url);
        Request request = new Request.Builder()
                //.header("authorization", cache.getToken())
                .removeHeader("User-Agent")
                .addHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
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
                int index3 = result.indexOf("403");
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