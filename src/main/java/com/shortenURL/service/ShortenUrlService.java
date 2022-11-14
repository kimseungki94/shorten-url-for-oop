package com.shortenURL.service;

import com.shortenURL.domain.ShortenUrl;
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
        ShortenUrl shortenUrl = shortenUrlRepository.createUrl(createUrlDto.shortenUrlDomainConverter(createUrlDto)).get();
        Optional<CreateUrlDto> responseCreateUrlDto = Optional.ofNullable(createUrlDto.shortenUrlDtoConverter(shortenUrl));
        return responseCreateUrlDto;
    }

    public Optional<FindRealUrlDto> findByRealUrl(String key) {
        FindRealUrlDto findRealUrlDto = new FindRealUrlDto(key);
        ShortenUrl shortenUrl = shortenUrlRepository.findByRealUrl(findRealUrlDto.shortenUrlDomainConverter(findRealUrlDto)).get();
        Optional<FindRealUrlDto> requestFindRealUrlDto = Optional.ofNullable(findRealUrlDto.shortenUrlDtoConverter(shortenUrl));
        return requestFindRealUrlDto;
    }

    public Optional<FindRequestCountDto> searchCount(String key) {
        FindRequestCountDto findRequestCountDto = new FindRequestCountDto(key);
        ShortenUrl shortenUrl = shortenUrlRepository.findByRequestCount(findRequestCountDto.shortenUrlDomainConverter(findRequestCountDto)).get();
        Optional<FindRequestCountDto> responseFindRequestCountDto = Optional.ofNullable(findRequestCountDto.shortenUrlDtoConverter(shortenUrl));
        return responseFindRequestCountDto;
    }
}
