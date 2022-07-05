package com.example.urlshortenerservice.controller

import com.example.urlshortenerservice.controller.UrlController
import com.example.urlshortenerservice.service.UrlService
import com.example.urlshortenerservice.service.impl.UrlServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class UrlControllerTest extends Specification {

    private UrlController urlController
    private UrlServiceImpl urlService
    private MockMvc mockMvc

    @BeforeEach
    void setup() {
        urlService = Mock()
        urlController = new UrlController(urlService)
        mockMvc = MockMvcBuilders.standaloneSetup(urlController).build()
    }

    def "test redirect"() {
        given:
        def shortUrl = "abc"
        def originalUrl = "http://localhost/abc"
        def endpoint = "/shortUrl/" + shortUrl

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint).
                contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * urlService.getOriginalUrl(originalUrl)
        def response = result.response
        noExceptionThrown()
        response.status == 200
    }
}
