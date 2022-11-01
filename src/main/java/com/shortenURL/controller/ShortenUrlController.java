package com.shortenURL.controller;

import com.shortenURL.domain.ShortenUrl;
import com.shortenURL.dto.SuccessDto;
import com.shortenURL.service.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
public class ShortenUrlController {
    @Autowired
    ShortenUrlService shortenUrlService;

    @PostMapping("/create")
    public ResponseEntity<SuccessDto> createUrl(@RequestBody ShortenUrl url) {
        return shortenUrlService.createUrl(url.getUrl());
    }

    @GetMapping("/{key}")
    public ResponseEntity searchUrl(@PathVariable("key") String key) throws URISyntaxException {
        return shortenUrlService.findUrl(key);
    }

    @GetMapping("/count/{key}")
    public ResponseEntity<SuccessDto> searchCount(@PathVariable("key") String key) {
        return shortenUrlService.searchCount(key);
    }

}
