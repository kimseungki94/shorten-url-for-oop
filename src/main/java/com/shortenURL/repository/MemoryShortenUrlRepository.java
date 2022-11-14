package com.shortenURL.repository;

import com.shortenURL.domain.ShortenUrl;
import com.shortenURL.utils.EncodingUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MemoryShortenUrlRepository implements ShortenUrlRepository {

    private static ArrayList<ShortenUrl> list = new ArrayList<>();
    private static int sequence = 1000000000;

    @Override
    public Optional<ShortenUrl> createUrl(ShortenUrl requestShortenUrl) {
        Optional<ShortenUrl> searchData = Optional.of(searchSameRealUrl(requestShortenUrl.getRealUrl())
                .orElseGet(() -> initCreateShortenUrl(requestShortenUrl)));

        return Optional.of(new ShortenUrl(searchData.get().getShortUrl(), searchData.get().getRealUrl(), searchData.get().getConnectCount()));
    }

    @Override
    public Optional<ShortenUrl> findByRealUrl(ShortenUrl shortenUrl) {
        return Optional.ofNullable(new ShortenUrl(searchSameShortenUrl(shortenUrl.getRealUrl()).get().getRealUrl()));
    }

    @Override
    public Optional<ShortenUrl> findByRequestCount(ShortenUrl requestShortenUrl) {
        ShortenUrl shortenUrl = searchSameShortenUrl(requestShortenUrl.getShortUrl()).orElseThrow();
        int index = list.indexOf(shortenUrl);
        ShortenUrl editShortenUrl = new ShortenUrl(shortenUrl.getShortUrl(), shortenUrl.getRealUrl(), shortenUrl.getConnectCount() + 1);
        list.set(index, editShortenUrl);
        return Optional.ofNullable(editShortenUrl);
    }

    public Optional<ShortenUrl> searchSameShortenUrl(String shortenUrl) {
        return list.stream().filter(url -> shortenUrl.equals(url.getShortUrl())).findFirst();
    }

    public Optional<ShortenUrl> searchSameRealUrl(String realUrl) {
        return list.stream().filter(url -> realUrl.equals(url.getRealUrl())).findFirst();
    }

    private ShortenUrl initCreateShortenUrl(ShortenUrl requestShortenUrl) {
        String shortUrl = EncodingUtils.base62Encoding(sequence);
        list.add(new ShortenUrl(shortUrl, requestShortenUrl.getRealUrl()));
        sequence++;
        return new ShortenUrl(shortUrl, requestShortenUrl.getRealUrl(), 0);
    }
}
