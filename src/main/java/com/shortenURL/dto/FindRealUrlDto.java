package com.shortenURL.dto;

public class FindRealUrlDto {
    String realUrl;

    public FindRealUrlDto(String realUrl) {
        this.realUrl = realUrl;
    }

    public String getRealUrl() {
        return realUrl;
    }
}
