package com.automation.regression.service;

import com.automation.regression.rest.model.ContextBook;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.Book;
import org.springframework.stereotype.Service;

import static org.testng.AssertJUnit.assertEquals;

@Service
@ScenarioScope
public class BookVerifyService {
    private static final Logger logger = LogManager.getLogger(BookVerifyService.class);

    public void verifyBook(final ContextBook expBook, final Book actBook) {
        assertEquals("Expected id is not matching with the actual", actBook.getId(), expBook.getId());
        assertEquals("Expected author is not matching with the actual", actBook.getAuthor(), expBook.getAuthor());
        assertEquals("Expected user id is not matching with the actual", actBook.getUserId(), expBook.getUserId());
        assertEquals("Expected title is not matching with the actual", actBook.getTitle(), expBook.getTitle());
    }
}
