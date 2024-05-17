package com.automation.regression.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.UsersApiController;


public class Client{
    final UsersApiController usersApiController;
    final ObjectMapper objectMapper;
    public Client(UsersApiController usersApiController) {
        this.usersApiController = usersApiController;
        this.objectMapper = new ObjectMapper();

    }

}
