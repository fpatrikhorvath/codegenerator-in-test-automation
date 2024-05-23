package com.automation.regression.rest.model;

import org.openapitools.model.Book;

public class ContextBook extends Book {
    private String contextId = null;

    public ContextBook() {
    }

    public void setContextId(final String contextId) {
        this.contextId = contextId;
    }
}
