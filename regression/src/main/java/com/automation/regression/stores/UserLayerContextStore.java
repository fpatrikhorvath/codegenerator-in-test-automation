package com.automation.regression.stores;

import com.automation.regression.service.BookService;
import com.automation.regression.service.BookVerifyService;
import com.automation.regression.service.UserService;
import com.automation.regression.service.UserVerifyService;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserLayerContextStore {
    private final UserService userService;
    private final UserVerifyService userVerifyService;
    private final BookService bookService;
    private final BookVerifyService bookVerifyService;

    public UserLayerContextStore(final UserService userService, final UserVerifyService userVerifyService, final BookService bookService, final BookVerifyService bookVerifyService) {
        this.userService = userService;
        this.userVerifyService = userVerifyService;
        this.bookService = bookService;
        this.bookVerifyService = bookVerifyService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserVerifyService getUserVerifyService() {
        return userVerifyService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public BookVerifyService getBookVerifyService() {
        return bookVerifyService;
    }
}
