package com.automation.regression.service;

import com.automation.regression.rest.clients.UserClient;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.CreateUser201ResponseDTO;
import org.openapitools.model.CreateUserRequestDTO;
import org.openapitools.model.UserDTO;
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

    public UserDTO initContextUser(final String statusString) {
        UserDTO user = new UserDTO();

        UserDTO.StatusEnum status = UserDTO.StatusEnum.valueOf(statusString);

        user.setName(randomService.getRandomString(10));
        user.setEmail(randomService.getRandomString(7) + "@gmail.com");
        user.setStatus(status);

        //logger.debug("Context user: {}", user);
        System.out.println("Context user: " + user);
        return user;
    }

    public ResponseEntity<CreateUser201ResponseDTO> registerUser(final UserDTO user) {

        CreateUserRequestDTO body = new CreateUserRequestDTO();

        CreateUserRequestDTO.StatusEnum status = CreateUserRequestDTO.StatusEnum.valueOf(user.getStatus().toString());

        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setStatus(status);

        return userClient.createUser(body);
    }

    public ResponseEntity<List<UserDTO>> getUsers() {
        return userClient.getUsers();
    }


    public ResponseEntity<Void> deleteUser(final Long userId) {
        return userClient.deleteUser(userId);
    }
}
