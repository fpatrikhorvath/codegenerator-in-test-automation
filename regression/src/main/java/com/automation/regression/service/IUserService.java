package com.automation.regression.service;

import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.User;
import org.springframework.http.ResponseEntity;

//@Component
//@ScenarioScope
public interface IUserService {

    User initUser(String statusString);

    ResponseEntity<CreateUser201Response> registerUser(User user);
}
