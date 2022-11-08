package com.shortenURL.dto;

public class CreateUrlDto {
    private String url;
    private String changeUrl;
    private long connectCount;

    public CreateUrlDto(String url, String changeUrl, long connectCount) {
        this.url = url;
        this.changeUrl = changeUrl;
        this.connectCount = connectCount;
    }

    public String getUrl() {
        return url;
    }

    public String getChangeUrl() {
        return changeUrl;
    }

    public long getConnectCount() {
        return connectCount;
    }
}
