package com.jarvanmo.tttv.controllers;

import com.jarvanmo.tttv.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-10.
 */

public class ChildVideoCtrl extends OkhttpService{
    private String baseUrl = "/lua/";
    public void applyVisitor(OkHttpClient okHttpClient, OnResponseListener listener) {
        String url = baseUrl;
        this.doGet(okHttpClient, url, listener);
    }
}