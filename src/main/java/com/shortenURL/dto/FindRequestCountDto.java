package com.shortenURL.dto;

import com.shortenURL.domain.ShortenUrl;

public class FindRequestCountDto {
    String url;
    long connectCount;

    public FindRequestCountDto(String url) {
        this.url = url;
    }

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

    public ShortenUrl shortenUrlDomainConverter(FindRequestCountDto findRequestCountDto) {
        ShortenUrl shortenUrl = new ShortenUrl(findRequestCountDto.getUrl(),0);
        return shortenUrl;
    }

    public FindRequestCountDto shortenUrlDtoConverter(ShortenUrl shortenUrl) {
        return new FindRequestCountDto(shortenUrl.getShortUrl(), shortenUrl.getConnectCount());
    }
}
