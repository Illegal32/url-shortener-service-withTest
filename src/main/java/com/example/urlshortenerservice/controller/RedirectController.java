package com.example.urlshortenerservice.controller;

import com.example.urlshortenerservice.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@Controller
@CrossOrigin
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("{shortUrl}")
    ResponseEntity<Void> redirect(@PathVariable("shortUrl") String shortUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.getOriginalUrl(String.format("http://localhost/%s", shortUrl)))).build();
    }

}
