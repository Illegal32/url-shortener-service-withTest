package com.example.urlshortenerservice.service.impl

import com.example.urlshortenerservice.model.UrlEntity
import com.example.urlshortenerservice.repository.UrlRepository
import spock.lang.Specification

class UrlServiceImplTest extends Specification {

    UrlRepository urlRepository = Mock();

    UrlServiceImpl urlService = new UrlServiceImpl(urlRepository);

    def "GenerateShortUrl"() {

        given:
        def shortUrl = "http://localhost/";
        def originalUrl = "https://www.youtube.com/watch?v=NzVi0_8KV3o&ab_channel=KnoldusInc.";

        when:
        def result = urlService.generateShortUrl(originalUrl)

        then:
        result.startsWith(shortUrl)

        1 * urlRepository.save(_);
        noExceptionThrown();

    }

    def "GetOriginalUrl"() {

        given:
        def shortUrl = "http://localhost/8138ad0c";
        def originalUrl = "http://"
        def url = UrlEntity.builder()
                .id(1)
                .originalUrl(originalUrl)
                .shortUrl(shortUrl)
                .build()

        when:
        def result = urlService.getOriginalUrl(shortUrl)

        then:
        1 * urlRepository.findByShortUrl(shortUrl) >> url
        noExceptionThrown()

    }
}
