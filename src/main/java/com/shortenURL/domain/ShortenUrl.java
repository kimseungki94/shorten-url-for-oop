package com.shortenURL.domain;

import org.springframework.stereotype.Component;

public class ShortenUrl {
    private String url;
    private String realUrl;
    private long connectCount;

    public void insertDefaultData(String url, String realUrl) {
        this.url = url;
        this.realUrl = realUrl;
        this.connectCount = 0;
    }

    public String getUrl() {
        return url;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public long getConnectCount() {
        return connectCount;
    }

    public void countConnect() {
        this.connectCount += 1;
    }
}
