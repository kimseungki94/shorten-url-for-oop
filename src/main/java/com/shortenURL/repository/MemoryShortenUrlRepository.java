package com.shortenURL.repository;

import com.shortenURL.dto.CreateUrlDto;
import com.shortenURL.dto.FindRealUrlDto;
import com.shortenURL.dto.FindRequestCountDto;
import com.shortenURL.entity.ShortenUrl;
import com.shortenURL.utils.EncodingUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MemoryShortenUrlRepository implements ShortenUrlRepository {

    private static ArrayList<ShortenUrl> list = new ArrayList<>();
    private static int sequence = 1000000000;

    @Override
    public Optional<CreateUrlDto> createUrl(CreateUrlDto createUrlDto) {
        Optional<ShortenUrl> searchData = searchSameRealUrl(createUrlDto.getUrl());
        if (!searchData.isEmpty()) {
            return Optional.of(new CreateUrlDto(searchData.get().getShortUrl(), searchData.get().getRealUrl(), searchData.get().getConnectCount()));
        } else {
            String shortUrl = EncodingUtils.base62Encoding(sequence);
            list.add(new ShortenUrl(shortUrl, createUrlDto.getUrl()));
            sequence++;
            return Optional.of(new CreateUrlDto(createUrlDto.getUrl(), shortUrl, 0));
        }
    }

    @Override
    public Optional<FindRealUrlDto> findByRealUrl(String shortenUrl) {
        return Optional.ofNullable(new FindRealUrlDto(searchSameShortenUrl(shortenUrl).get().getRealUrl()));
    }

    @Override
    public Optional<FindRequestCountDto> findByRequestCount(String shortUrl) {
        Optional<ShortenUrl> shortenUrl = searchSameShortenUrl(shortUrl);
        if (!shortenUrl.isEmpty()) {
            int index = list.indexOf(shortenUrl.get());
            list.set(index, new ShortenUrl(shortenUrl.get().getShortUrl(), shortenUrl.get().getRealUrl(), shortenUrl.get().getConnectCount() + 1));
        }
        return Optional.of(new FindRequestCountDto(shortenUrl.get().getShortUrl(), shortenUrl.get().getConnectCount() + 1));
    }

    public Optional<ShortenUrl> searchSameShortenUrl(String shortenUrl) {
        return list.stream().filter(url -> shortenUrl.equals(url.getShortUrl())).findFirst();
    }

    public Optional<ShortenUrl> searchSameRealUrl(String realUrl) {
        return list.stream().filter(url -> realUrl.equals(url.getRealUrl())).findFirst();
    }
}
