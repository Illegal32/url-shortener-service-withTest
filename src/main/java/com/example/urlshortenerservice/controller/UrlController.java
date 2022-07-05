package com.example.urlshortenerservice.controller;

import com.example.urlshortenerservice.service.impl.UrlServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/shortUrl")
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlServiceImpl urlService;

    @GetMapping("/{shortUrl}")
    @ResponseStatus(HttpStatus.OK)
    public void redirect(@PathVariable("shortUrl") String shortUrl) {
        urlService.getOriginalUrl("http://localhost/"+ shortUrl);
    }

}