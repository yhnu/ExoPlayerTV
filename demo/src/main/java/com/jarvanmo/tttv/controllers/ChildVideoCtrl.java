package com.jarvanmo.tttv.controllers;

import com.jarvanmo.tttv.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-10.
 */

public class ChildVideoCtrl extends OkhttpService{
    public void applyVisitor(OkHttpClient okHttpClient, OnResponseListener listener) {
        this.doGet(okHttpClient, "https://yhnu.gitee.io/index.json", listener);
    }
}