package com.shortenURL.dto;

public class SuccessDto {
    int code;
    String message;
    String url;

    public void insertData(int code, String message,String url) {
        this.code = code;
        this.message = message;
        this.url=url;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
