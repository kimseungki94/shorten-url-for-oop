package com.shortenURL.controller;

import com.shortenURL.dto.CreateUrlDto;
import com.shortenURL.dto.FindRealUrlDto;
import com.shortenURL.service.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
public class ShortenUrlController {
    @Autowired
    ShortenUrlService shortenUrlService;

    HttpHeaders headers = new HttpHeaders();

    @PostMapping("/shorten-url")
    public ResponseEntity<Object> createUrl(@RequestBody CreateUrlDto createUrlDto) {
        return new ResponseEntity<>(shortenUrlService.createUrl(createUrlDto).get(), HttpStatus.OK);
    }

    @GetMapping("/shorten-url/{key}")
    public ResponseEntity searchUrl(@PathVariable("key") String key) throws URISyntaxException {
        Optional<FindRealUrlDto> byRealUrl = shortenUrlService.findByRealUrl(key);
        headers.setLocation(new URI(shortenUrlService.findByRealUrl(key).get().getRealUrl()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/shorten-url/count/{key}")
    public ResponseEntity<Object> searchCount(@PathVariable("key") String key) {
        return new ResponseEntity<>(shortenUrlService.searchCount(key), HttpStatus.OK);
    }
}
