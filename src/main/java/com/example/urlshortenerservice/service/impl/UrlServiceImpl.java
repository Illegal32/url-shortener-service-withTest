package com.example.urlshortenerservice.service.impl;

import com.example.urlshortenerservice.model.UrlEntity;
import com.example.urlshortenerservice.repository.UrlRepository;
import com.example.urlshortenerservice.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public String generateShortUrl(String originalUrl) {
        String shortUrl = String.format("http://localhost/%s", UUID.randomUUID().toString().substring(0, 8));
        System.out.printf("%s is generated for: %s%n", shortUrl, originalUrl);
        urlRepository.save(UrlEntity.builder()
                .shortUrl(shortUrl)
                .originalUrl(originalUrl)
                .build());
        return shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).getOriginalUrl();
    }
}
