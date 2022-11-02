package com.shortenURL.service;

import com.shortenURL.domain.ShortenUrl;
import com.shortenURL.dto.SuccessDto;
import com.shortenURL.repository.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class ShortenUrlService {

    @Autowired
    ShortenUrlRepository shortenUrlRepository;

    SuccessDto successDto = new SuccessDto();
    HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<SuccessDto> createUrl(String url) {
        Optional<ShortenUrl> shortenUrl = shortenUrlRepository.save(url);
        if (!shortenUrl.isEmpty()) {
            successDto.insertData(200, "success", shortenUrl.get().getUrl());
            return new ResponseEntity<>(successDto, HttpStatus.OK);
        }
        successDto.insertData(404, "fail", null);
        return new ResponseEntity<>(successDto, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity findUrl(String key) throws URISyntaxException {
        Optional<ShortenUrl> url = shortenUrlRepository.findByRealUrl(key);
        if (!url.isEmpty()) {
            headers.setLocation(new URI(url.get().getRealUrl()));
            return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
        } else {
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<SuccessDto> searchCount(String key) {
        Optional<ShortenUrl> url = shortenUrlRepository.findByRealUrl(key);
        if (!url.isEmpty()) {
            successDto.insertData(200, Long.toString(url.get().getConnectCount()), null);
            return new ResponseEntity<>(successDto, HttpStatus.OK);
        } else {
            successDto.insertData(400, Long.toString(url.get().getConnectCount()), null);
            return new ResponseEntity<>(successDto, HttpStatus.NOT_FOUND);
        }
    }
}
