package com.automation.regression.rest.clients;

import com.automation.regression.config.UserLayerConfig;
import com.automation.regression.rest.RestClient;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.api.BookApi;
import org.openapitools.model.Book;
import org.openapitools.model.CreateBookForUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ScenarioScope
public class BookClient implements BookApi {
    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public BookClient(final UserLayerConfig userLayerConfig, final RestClient restClient) {
        this.userLayerConfig = userLayerConfig;
        this.restClient = restClient;
    }

    @Override
    public ResponseEntity<Book> createBookForUser(final Long userId, final CreateBookForUserRequest body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteBookForUser(final Long userId, final Long bookId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Book>> getBooksForUser(final Long userId) {
        return null;
    }
}
