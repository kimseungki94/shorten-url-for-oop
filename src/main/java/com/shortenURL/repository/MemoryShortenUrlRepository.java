package com.shortenURL.repository;

import com.shortenURL.dto.ShortenUrlDto;
import com.shortenURL.utils.EncodingUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MemoryShortenUrlRepository implements ShortenUrlRepository {

    private static ArrayList<ShortenUrlDto> list = new ArrayList<>();
    private static int sequence = 0;

    private final ShortenUrlDto shortenUrl = new ShortenUrlDto();

    @Override
    public Optional<ShortenUrlDto> save(String url) {
        String id = EncodingUtils.base62Encoding(sequence);
        Optional<ShortenUrlDto> searchData = searchSameUrl(id);
        if (!searchData.isEmpty()) {
            return searchData;
        } else {
            shortenUrl.insertDefaultData(id, url);
            list.add(shortenUrl);
            sequence++;
            return Optional.ofNullable(shortenUrl);
        }
    }

    @Override
    public Optional<ShortenUrlDto> findByRealUrl(String key) {
        Optional<ShortenUrlDto> searchData = searchSameUrl(key);
        if (!searchData.isEmpty()) {
            searchData.get().countConnect();
            return searchData;
        } else {
            return null;
        }
    }

    public Optional<ShortenUrlDto> searchSameUrl(String key) {
        return list.stream().filter(url -> url.getUrl().equals(key)).findFirst();
    }
}
