package com.automation.regression.rest.model;

import org.openapitools.model.User;

public class ContextUser extends User {
    private String contextId = null;

    public ContextUser() {
    }

    public void setContextId(final String contextId) {
        this.contextId = contextId;
    }
}
