package com.example.urlshortenerservice.repository;

import com.example.urlshortenerservice.model.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlEntity, Integer> {
    UrlEntity findByShortUrl(String shortUrl);
}
