package com.example.urlshortenerservice.controller

import com.example.urlshortenerservice.controller.RedirectController
import com.example.urlshortenerservice.service.impl.UrlServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class RedirectControllerTest extends Specification {

    private UrlServiceImpl urlService
    private RedirectController redirectController
    private MockMvc mockMvc

    @BeforeEach
    void setup() {
        urlService = Mock()
        redirectController = new RedirectController(urlService)
        mockMvc = MockMvcBuilders.standaloneSetup(redirectController).build()
    }


    def "test redirect"() {

        given:
        def shortUrl = "S"
        def url = "http://localhost/S"
        def endpoint = "/shortUrl/" + shortUrl
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * urlService.getOriginalUrl(url)
        def response = result.response
        response.status == 200

    }
}