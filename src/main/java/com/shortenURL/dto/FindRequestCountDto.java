package com.shortenURL.dto;

public class FindRequestCountDto {
    String url;
    long connectCount;

    public FindRequestCountDto(String url, long connectCount) {
        this.url = url;
        this.connectCount = connectCount;
    }

    public String getUrl() {
        return url;
    }

    public long getConnectCount() {
        return connectCount;
    }
}
