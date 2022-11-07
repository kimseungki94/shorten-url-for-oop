package com.shortenURL.entity;

public class ShortenUrl {
    private String shortUrl;
    private String realUrl;
    private long connectCount;

    public ShortenUrl(String shortUrl, String realUrl) {
        this.shortUrl = shortUrl;
        this.realUrl = realUrl;
        this.connectCount = 0;
    }

    public ShortenUrl(String shortUrl, String realUrl, long connectCount) {
        this.shortUrl = shortUrl;
        this.realUrl = realUrl;
        this.connectCount = connectCount;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public long getConnectCount() {
        return connectCount;
    }
}