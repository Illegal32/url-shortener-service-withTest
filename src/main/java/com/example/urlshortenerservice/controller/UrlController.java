package com.example.urlshortenerservice.controller;

import com.example.urlshortenerservice.service.UrlService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("")
    public String generateShortUrl(@RequestParam("originalUrl") String originalUrl) {
        return urlService.generateShortUrl(originalUrl);
    }

}
