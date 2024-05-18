package com.automation.regression.service;

import io.cucumber.spring.ScenarioScope;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//@Component
//@ScenarioScope
public interface IUserService {
    //TODO: enum handling
    ResponseEntity<CreateUser201Response> createUser(User user);
}
