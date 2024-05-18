package com.automation.regression.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.CreateUser201Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML;

public class RestClient {
    private static final Logger logger = LogManager.getLogger(RestClient.class);
    private final WebClient webClient;
    private final HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    private String url;

    public RestClient(final String url, final HttpHeaders headers) {
        //TODO: add these
        //headers.forEach(headers::add);
        this.url = url;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(this.headers)).build();
        logger.trace(webClient);
    }

    public <T> ResponseEntity<T> post(final String endpoint, final Object payload, final Class<T> clazz) {
        ResponseEntity<T> response = webClient
                .post()
                .uri(endpoint)
                .bodyValue(payload)
                .retrieve()
                .toEntity(clazz)
                .block();
        logger.debug("Response: {}", response);
        return response;
    }
}
