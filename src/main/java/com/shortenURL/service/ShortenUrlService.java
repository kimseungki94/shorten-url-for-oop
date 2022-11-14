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
        return Optional.ofNullable(
                createUrlDto.shortenUrlDtoConverter(
                        shortenUrlRepository.createUrl(createUrlDto.shortenUrlDomainConverter(createUrlDto)).get()));
    }

    public Optional<FindRealUrlDto> findByRealUrl(String key) {
        FindRealUrlDto findRealUrlDto = new FindRealUrlDto(key);
        return Optional.ofNullable(findRealUrlDto.shortenUrlDtoConverter(
                shortenUrlRepository.findByRealUrl(
                        findRealUrlDto.shortenUrlDomainConverter(findRealUrlDto)).get()));
    }

    public Optional<FindRequestCountDto> searchCount(String key) {
        FindRequestCountDto findRequestCountDto = new FindRequestCountDto(key);
        return Optional.ofNullable(findRequestCountDto.shortenUrlDtoConverter(
                shortenUrlRepository.findByRequestCount(
                        findRequestCountDto.shortenUrlDomainConverter(findRequestCountDto)).get()));
    }
}
