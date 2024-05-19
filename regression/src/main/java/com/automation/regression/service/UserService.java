package com.automation.regression.service;

import com.automation.regression.rest.clients.UserClient;
import com.automation.regression.rest.model.ContextUser;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ScenarioScope
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserClient userClient;
    private final RandomService randomService;

    public UserService(final UserClient userClient, final RandomService randomService) {
        this.userClient = userClient;
        this.randomService = randomService;
    }

    public ContextUser initContextUser(final String contextId, final String statusString) {
        ContextUser user = new ContextUser();

        User.StatusEnum status = User.StatusEnum.valueOf(statusString);

        user.setName(randomService.getRandomString(10));
        user.setEmail(randomService.getRandomString(7) + "@gmail.com");
        user.setStatus(status);

        //logger.debug("Context user: {}", user);
        System.out.println("Context user: " + user);
        return user;
    }

    public ResponseEntity<CreateUser201Response> registerUser(final ContextUser user) {
        CreateUserRequest body = new CreateUserRequest();

        CreateUserRequest.StatusEnum status = CreateUserRequest.StatusEnum.valueOf(user.getStatus().toString());

        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setStatus(status);

        return userClient.createUser(body);
    }

    public ResponseEntity<List<User>> getUsers() {
        return userClient.getUsers();
    }

}
