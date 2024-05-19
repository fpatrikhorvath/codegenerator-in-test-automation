package com.automation.regression.rest.clients;

import com.automation.regression.config.UserLayerConfig;
import com.automation.regression.rest.RestClient;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.api.UserApi;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ScenarioScope
public class UserClient implements UserApi {
    private static final String CREATE_USER_PATH = "/users";
    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public UserClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }

    @Override
    public ResponseEntity<CreateUser201Response> createUser(final CreateUserRequest body) {
        return restClient.post(CREATE_USER_PATH, body, CreateUser201Response.class);
    }

    @Override
    public ResponseEntity<Void> deleteUser(final Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return null;
    }
}
