package com.shortenURL.dto;

import com.shortenURL.domain.ShortenUrl;

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

    public ShortenUrl shortenUrlDomainConverter(CreateUrlDto createUrlDto) {
        ShortenUrl shortenUrl = new ShortenUrl(createUrlDto.getChangeUrl(), createUrlDto.getUrl(),0);
        return shortenUrl;
    }

    public CreateUrlDto shortenUrlDtoConverter(ShortenUrl shortenUrl) {
        CreateUrlDto createUrlDto = new CreateUrlDto(shortenUrl.getRealUrl(), shortenUrl.getShortUrl(),shortenUrl.getConnectCount());
        return createUrlDto;
    }
}
