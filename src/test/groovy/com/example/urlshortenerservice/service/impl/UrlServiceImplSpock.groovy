package com.example.urlshortenerservice.service.impl

import com.example.urlshortenerservice.model.UrlEntity
import com.example.urlshortenerservice.repository.UrlRepository
import com.example.urlshortenerservice.service.UrlService
import spock.lang.Specification

class UrlServiceImplSpock extends Specification {

    private UrlRepository urlRepository = Mock()
    private UrlService urlService = new UrlServiceImpl(urlRepository)


    def "test generateShortUrl()"() {
        given:
        def originalUrl = "https://mvnrepository.com/artifact/org.spockframework/spock-core/2.1-M2-groovy-3.0"
        def shortUrl = "http://localhost/"

        when:
        def result = urlService.generateShortUrl(originalUrl)

        then:
        1 * urlRepository.save(_)
        result.startsWith(shortUrl)
        noExceptionThrown()
    }

    def "test getOriginalUrl()"() {
        given:
        def originalUrl = "https://mvnrepository.com/artifact/org.spockframework/spock-core/2.1-M2-groovy-3.0"
        def shortUrl = "http://localhost/2067f7f9"
        def urlEntity = UrlEntity.builder().shortUrl(shortUrl).originalUrl(originalUrl).build()

        when:
        def result = urlService.getOriginalUrl(shortUrl)

        then:
        1 * urlRepository.findByShortUrl(shortUrl) >> urlEntity
        result == originalUrl
        noExceptionThrown()
    }
}
