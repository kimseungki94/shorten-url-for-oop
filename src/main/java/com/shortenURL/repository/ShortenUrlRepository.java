package com.shortenURL.repository;

import com.shortenURL.dto.ShortenUrlDto;

import java.util.Optional;

public interface ShortenUrlRepository {
    public Optional<ShortenUrlDto> save(String id);
    public Optional<ShortenUrlDto> findByRealUrl(String key);

}
