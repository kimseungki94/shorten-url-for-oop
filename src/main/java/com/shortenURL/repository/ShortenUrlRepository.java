package com.shortenURL.repository;

import com.shortenURL.domain.ShortenUrl;

import java.util.Optional;

public interface ShortenUrlRepository {
    public Optional<ShortenUrl> createUrl(ShortenUrl shortenUrl);
    public Optional<ShortenUrl> findByRealUrl(ShortenUrl shortenUrl);
    public Optional<ShortenUrl> findByRequestCount(ShortenUrl shortenUrl);

}
