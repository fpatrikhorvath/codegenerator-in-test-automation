package com.automation.regression.rest.api;

import com.automation.regression.rest.model.GenericErrorResponse;
import org.openapitools.model.CreateBookForUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;

public interface BookApiExtension {
    /**
     * POST /users/{userId}/books : Create a book for a user
     *
     * @param userId                   ID of the user to create a book for (required)
     * @param createBookForUserRequest (required)
     * @return User not found (status code 404)
     * or internal error (status code 500)
     */
    @HttpExchange(
            method = "POST",
            value = "/users/{userId}/books",
            accept = {"application/json"},
            contentType = "application/json"
    )
    ResponseEntity<GenericErrorResponse> createBookForUserNegative(
            @PathVariable("userId") Long userId,
            @RequestBody CreateBookForUserRequest createBookForUserRequest
    );
}
