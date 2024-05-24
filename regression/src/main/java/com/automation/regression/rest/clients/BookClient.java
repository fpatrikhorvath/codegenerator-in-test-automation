package com.automation.regression.rest.clients;

import com.automation.regression.config.UserLayerConfig;
import com.automation.regression.rest.RestClient;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.api.BookApi;
import org.openapitools.model.Book;
import org.openapitools.model.CreateBookForUser404Response;
import org.openapitools.model.CreateBookForUserRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@ScenarioScope
public class BookClient implements BookApi {
    private static final String GET_BOOK_PATH = "/users/{userId}/books";
    private static final String POST_BOOK_PATH = "/users/{userId}/books";
    private static final String DELETE_BOOK_PATH = "/users/{userId}/books/{bookId}";
    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public BookClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }

    @Override
    public ResponseEntity<Book> createBookForUser(final Long userId, final CreateBookForUserRequest body) {
        String endpoint = StringUtils.replace(POST_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.post(endpoint, body, Book.class);
    }

    public ResponseEntity<CreateBookForUser404Response> registerBook4xxAnd5xx(final Long userId, final CreateBookForUserRequest body) {
        String endpoint = StringUtils.replace(POST_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.post(endpoint, body, CreateBookForUser404Response.class);
    }

    @Override
    public ResponseEntity<Void> deleteBookForUser(final Long userId, final Long bookId) {
        String endpoint = StringUtils
                .replace(DELETE_BOOK_PATH, "{userId}", String.valueOf(userId))
                .replace("{bookId}", String.valueOf(bookId));
        return restClient.delete(endpoint, Void.class);
    }

    @Override
    public ResponseEntity<List<Book>> getBooksForUser(final Long userId) {
        String endpoint = StringUtils
                .replace(GET_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.getList(endpoint, Book.class);
    }
}
