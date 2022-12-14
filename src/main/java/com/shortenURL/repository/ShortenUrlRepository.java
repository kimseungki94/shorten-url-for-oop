package com.shortenURL.repository;

import com.shortenURL.domain.ShortenUrl;

import java.util.Optional;

public interface ShortenUrlRepository {
    public Optional<ShortenUrl> save(String id);
    public Optional<ShortenUrl> findByRealUrl(String key);

}
