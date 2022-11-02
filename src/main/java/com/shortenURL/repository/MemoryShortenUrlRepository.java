package com.shortenURL.repository;

import com.shortenURL.parse.TextParse;
import com.shortenURL.domain.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MemoryShortenUrlRepository implements ShortenUrlRepository {

    private static ArrayList<ShortenUrl> list = new ArrayList<>();
    private static int sequence = 0;

    private ShortenUrl shortenUrl= new ShortenUrl();
    @Autowired
    private TextParse textParse;

    @Override
    public Optional<ShortenUrl> save(String url) {
        String id = textParse.encoding(sequence);
        Optional<ShortenUrl> searchData = list.stream().filter(data -> data.getRealUrl().equals(id)).findFirst();
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
    public Optional<ShortenUrl> findByRealUrl(String key) {
        Optional<ShortenUrl> searchData = list.stream().filter(url -> url.getUrl().equals(key)).findFirst();
        if(!searchData.isEmpty()) {
            searchData.get().countConnect();
            return searchData;
        } else {
            return null;
        }
    }
}
