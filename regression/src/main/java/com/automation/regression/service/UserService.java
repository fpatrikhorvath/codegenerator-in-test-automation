package com.automation.regression.service;

import com.automation.regression.rest.clients.UserClient;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserService implements IUserService{
    private final UserClient userClient;
    private final RandomService randomService;
    public UserService(final UserClient userClient, final RandomService randomService) {
        this.userClient = userClient;
        this.randomService = randomService;
    }
    @Override
    public User initUser(final String statusString){
        User user = new User();

        User.StatusEnum status = User.StatusEnum.valueOf(statusString);

        user.setName(randomService.getRandomString(10));
        user.setEmail(randomService.getRandomString(3) + "@gmail.com");
        user.setStatus(status);

        return user;
    }
    //TODO: enum handling
    @Override
    public ResponseEntity<CreateUser201Response> registerUser(final User user){
        CreateUserRequest body = new CreateUserRequest();

        CreateUserRequest.StatusEnum status = CreateUserRequest.StatusEnum.valueOf(user.getStatus().toString());

        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setStatus(status);
        return userClient.createUser(body);
    }
}
