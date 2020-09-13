package com.jarvanmo.demo.models;

public class ChildVideoModel {
    String ip;
    String[] urls;

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getIP() {
        return ip;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public String[] getUrls() {
        return this.urls;
    }
}