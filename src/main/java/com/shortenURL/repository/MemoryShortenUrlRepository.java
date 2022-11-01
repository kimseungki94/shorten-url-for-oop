package com.shortenURL.repository;

import com.shortenURL.domain.ShortenUrl;
import com.shortenURL.util.Base62Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MemoryShortenUrlRepository implements ShortenUrlRepository {

    private static ArrayList<ShortenUrl> list = new ArrayList<>();
    private static int sequence = 0;

    @Autowired
    private ShortenUrl shortenUrl;

    @Autowired
    private Base62Util base62Util;

    @Override
    public synchronized Optional<ShortenUrl> save(String url) {
        String id = base62Util.encoding(sequence);
        Optional<ShortenUrl> searchData = list.stream().filter(data -> data.getRealUrl().equals(id)).findFirst();
        if (!searchData.isEmpty()) {
            return searchData;
        } else {
            shortenUrl.insertData(id, url);
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
