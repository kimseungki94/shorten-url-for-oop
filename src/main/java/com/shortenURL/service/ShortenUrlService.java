package com.shortenURL.service;

import com.shortenURL.dto.ShortenUrlDto;
import com.shortenURL.controller.response.SuccessResponse;
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

    SuccessResponse successResponse = new SuccessResponse();
    HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<SuccessResponse> createUrl(String url) {
        Optional<ShortenUrlDto> shortenUrl = shortenUrlRepository.save(url);
        if (!shortenUrl.isEmpty()) {
            successResponse.insertData(HttpStatus.OK.value(), "success", shortenUrl.get().getUrl());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        successResponse.insertData(HttpStatus.BAD_REQUEST.value(), "fail", null);
        return new ResponseEntity<>(successResponse, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity findUrl(String key) throws URISyntaxException {
        Optional<ShortenUrlDto> url = shortenUrlRepository.findByRealUrl(key);
        if (!url.isEmpty()) {
            headers.setLocation(new URI(url.get().getRealUrl()));
            return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
        } else {
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<SuccessResponse> searchCount(String key) {
        Optional<ShortenUrlDto> url = shortenUrlRepository.findByRealUrl(key);
        if (!url.isEmpty()) {
            successResponse.insertData(HttpStatus.OK.value(), Long.toString(url.get().getConnectCount()), null);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            successResponse.insertData(HttpStatus.BAD_REQUEST.value(), Long.toString(url.get().getConnectCount()), null);
            return new ResponseEntity<>(successResponse, HttpStatus.NOT_FOUND);
        }
    }
}
