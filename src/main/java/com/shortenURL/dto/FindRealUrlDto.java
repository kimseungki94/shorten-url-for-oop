package com.shortenURL.dto;

import com.shortenURL.domain.ShortenUrl;

public class FindRealUrlDto {
    String realUrl;

    public FindRealUrlDto(String realUrl) {
        this.realUrl = realUrl;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public ShortenUrl shortenUrlDomainConverter(FindRealUrlDto findRealUrlDto) {
        return new ShortenUrl(findRealUrlDto.getRealUrl());
    }

    public FindRealUrlDto shortenUrlDtoConverter(ShortenUrl shortenUrl) {
        return new FindRealUrlDto(shortenUrl.getRealUrl());
    }
}
