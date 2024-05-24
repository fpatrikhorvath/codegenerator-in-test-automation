package com.automation.regression.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class RestClient {
    private static final Logger logger = LogManager.getLogger(RestClient.class);
    private final WebClient webClient;
    private final HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    private String url;

    public RestClient(final String url, final HttpHeaders headers) {

        headers.forEach(this.headers::addAll);

        this.url = url;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(this.headers)).build();
        //logger.trace(webClient);
    }

    public <T> ResponseEntity<List<T>> getList(final String endpoint, final Class<T> clazz) {
        logCurl("GET", endpoint, null);

        ResponseEntity<List<T>> response = webClient
                .get()
                .uri(endpoint)
                .exchangeToMono(clientResponse -> clientResponse.toEntityList(clazz))
                .block();

        logger.debug("Response: {}", response);
        return response;
    }

    public <T> ResponseEntity<T> post(final String endpoint, final Object body, final Class<T> clazz) {
        logCurl("POST", endpoint, body);

        ResponseEntity<T> response = webClient
                .post()
                .uri(endpoint)
                .bodyValue(body)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(clazz))
                .block();
        System.out.println("Response: " + response);
        return response;
    }

    public <T> ResponseEntity<T> delete(final String endpoint, final Class<T> clazz) {
        logCurl("DELETE", endpoint, null);
        ResponseEntity<T> response = webClient
                .delete()
                .uri(endpoint)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(clazz))
                .block();
        //logger.debug("Response: {}", response);
        System.out.println("Response: " + response);
        return response;
    }

    private void logCurl(final String requestType, final String endpoint, final Object body) {
        StringBuilder curlCommand = new StringBuilder("curl -X ").append(requestType).append(" ");

        headers.forEach((key, value) ->
                value.forEach(val -> curlCommand.append("-H \"").append(key).append(": ").append(val).append("\" ")));

        curlCommand.append(url).append(endpoint).append(" ");

        if (body != null) {
            try {
                String jsonPayload = mapper.writeValueAsString(body);
                curlCommand.append("-d '").append(jsonPayload).append("' ");
            } catch (JsonProcessingException e) {
                logger.error("Failed to convert payload to JSON", e);
            }
        }

        //logger.debug("Curl command: {}", curlCommand.toString().trim());
        System.out.println("Curl command: " + curlCommand.toString().trim());

    }
}
