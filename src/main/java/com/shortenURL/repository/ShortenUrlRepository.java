package com.shortenURL.repository;

import com.shortenURL.dto.CreateUrlDto;
import com.shortenURL.dto.FindRealUrlDto;
import com.shortenURL.dto.FindRequestCountDto;
import com.shortenURL.entity.ShortenUrl;

import java.util.Optional;

public interface ShortenUrlRepository {
    public Optional<CreateUrlDto> createUrl(CreateUrlDto createUrlDto);
    public Optional<FindRealUrlDto> findByRealUrl(String shortUrl);
    public Optional<FindRequestCountDto> findByRequestCount(String shortUrl);

}
