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

    @PostMapping("/")
    public ResponseEntity createUrl(@RequestBody CreateUrlDto createUrlDto) {
        Optional<CreateUrlDto> responseCreateUrlDto = shortenUrlService.createUrl(createUrlDto);
        return new ResponseEntity<>(responseCreateUrlDto.get(), HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity searchUrl(@PathVariable("key") String key) throws URISyntaxException {
        Optional<FindRealUrlDto> byRealUrl = shortenUrlService.findByRealUrl(key);
        headers.setLocation(new URI(byRealUrl.get().getRealUrl()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/shorten-url/{key}")
    public ResponseEntity searchCount(@PathVariable("key") String key) {
        return new ResponseEntity<>(shortenUrlService.searchCount(key), HttpStatus.OK);
    }
}
