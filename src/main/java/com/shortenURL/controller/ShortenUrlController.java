package com.shortenURL.controller;

import com.shortenURL.dto.ShortenUrlDto;
import com.shortenURL.controller.resonse.SuccessResponse;
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
    public ResponseEntity<SuccessResponse> createUrl(@RequestBody ShortenUrlDto url) {
        return shortenUrlService.createUrl(url.getUrl());
    }

    @GetMapping("/{key}")
    public ResponseEntity searchUrl(@PathVariable("key") String key) throws URISyntaxException {
        return shortenUrlService.findUrl(key);
    }

    @GetMapping("/count/{key}")
    public ResponseEntity<SuccessResponse> searchCount(@PathVariable("key") String key) {
        return shortenUrlService.searchCount(key);
    }

}
