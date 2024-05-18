package com.automation.regression.service;

import com.automation.regression.rest.clients.UserClient;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserService implements IUserService{
    private final UserClient userClient;
    //@Autowired
    public UserService(final UserClient userClient) {
        this.userClient = userClient;
    }
    //TODO: enum handling
    @Override
    public ResponseEntity<CreateUser201Response> createUser(final User user){
        CreateUserRequest body = new CreateUserRequest();
        body.setName(user.getName());
        body.setEmail(user.getEmail());
        CreateUserRequest.StatusEnum status = CreateUserRequest.StatusEnum.valueOf(user.getStatus().toString());
        body.setStatus(status);
        return userClient.createUser(body);
    }
}
