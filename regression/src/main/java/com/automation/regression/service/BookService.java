package com.automation.regression.service;

import com.automation.regression.rest.clients.BookClient;
import com.automation.regression.rest.model.ContextBook;
import com.automation.regression.rest.model.ContextUser;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.Book;
import org.openapitools.model.CreateBookForUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ScenarioScope
public class BookService {
    private static final Logger logger = LogManager.getLogger(BookService.class);

    private final BookClient bookClient;
    private final RandomService randomService;

    public BookService(final BookClient bookClient, final RandomService randomService) {
        this.bookClient = bookClient;
        this.randomService = randomService;
    }


    public ContextBook initContextBook(final String contextId, final Long userId) {
        ContextBook book = new ContextBook();

        book.setContextId(contextId);
        book.setUserId(userId);
        book.setAuthor(randomService.getRandomString(10));
        book.setTitle(randomService.getRandomString(10));

        //logger.debug("Context user: {}", user);
        System.out.println("Context book: " + book);
        return book;
    }

    public ResponseEntity<Book> registerBook(final ContextBook book) {

        CreateBookForUserRequest body = new CreateBookForUserRequest();
        body.setAuthor(book.getAuthor());
        body.setTitle(book.getTitle());

        return bookClient.createBookForUser(book.getUserId(), body);
    }

    public ResponseEntity<List<Book>> getBooks(final ContextBook book) {
        return bookClient.getBooksForUser(book.getUserId());
    }


    public ResponseEntity<Void> deleteBook(final ContextUser user, final ContextBook book) {
        return bookClient.deleteBookForUser(user.getId(), book.getUserId());
    }
}
