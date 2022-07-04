package com.example.urlshortenerservice.service;

public interface UrlService {
    String generateShortUrl(String originalUrl);

    String getOriginalUrl(String shortUrl);
}
