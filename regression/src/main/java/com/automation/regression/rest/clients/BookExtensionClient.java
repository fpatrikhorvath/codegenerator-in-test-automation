package com.automation.regression.rest.clients;

import com.automation.regression.config.UserLayerConfig;
import com.automation.regression.rest.RestClient;
import com.automation.regression.rest.api.BookApiExtension;
import com.automation.regression.rest.model.GenericErrorResponse;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.model.CreateBookForUserRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@ScenarioScope
public class BookExtensionClient implements BookApiExtension {
    private static final String GET_BOOK_PATH = "/users/{userId}/books";
    private static final String POST_BOOK_PATH = "/users/{userId}/books";
    private static final String DELETE_BOOK_PATH = "/users/{userId}/books/{bookId}";
    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public BookExtensionClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }


    @Override
    public ResponseEntity<GenericErrorResponse> createBookForUserNegative
            (final Long userId, final CreateBookForUserRequest body) {
        String endpoint = StringUtils.replace(POST_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.post(endpoint, body, GenericErrorResponse.class);
    }
}
