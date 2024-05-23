package com.automation.regression.stores;

import com.automation.regression.service.BookService;
import com.automation.regression.service.UserService;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserLayerContextStore {
    private final UserService userService;
    private final BookService bookService;

    public UserLayerContextStore(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
