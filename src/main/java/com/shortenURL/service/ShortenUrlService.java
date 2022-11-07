package com.shortenURL.service;

import com.shortenURL.dto.CreateUrlDto;
import com.shortenURL.dto.FindRealUrlDto;
import com.shortenURL.dto.FindRequestCountDto;
import com.shortenURL.repository.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortenUrlService {

    @Autowired
    ShortenUrlRepository shortenUrlRepository;

    public Optional<CreateUrlDto> createUrl(CreateUrlDto createUrlDto) {
        return shortenUrlRepository.createUrl(createUrlDto);
    }

    public Optional<FindRealUrlDto> findByRealUrl(String key) {
        return shortenUrlRepository.findByRealUrl(key);
    }

    public Optional<FindRequestCountDto> searchCount(String key) {
        return shortenUrlRepository.findByRequestCount(key);
    }
}
