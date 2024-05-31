package com.automation.regression.rest.clients;

import com.automation.regression.config.UserLayerConfig;
import com.automation.regression.rest.RestClient;
import io.cucumber.spring.ScenarioScope;
import org.openapitools.api.UserApi;
import org.openapitools.model.CreateUser201ResponseDTO;
import org.openapitools.model.CreateUserRequestDTO;
import org.openapitools.model.GenericErrorResponse;
import org.openapitools.model.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@ScenarioScope
public class UserClient implements UserApi {
    private static final String CREATE_USER_PATH = "/users";
    private static final String GET_USER_PATH = "/users";
    private static final String DELETE_USER_PATH = "/users/{userId}";

    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public UserClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }

    @Override
    public ResponseEntity<CreateUser201ResponseDTO> createUser(final CreateUserRequestDTO body) {
        return restClient.post(CREATE_USER_PATH, body, CreateUser201ResponseDTO.class);
    }


    @Override
    public ResponseEntity<GenericErrorResponse> createUserNeg(final CreateUserRequestDTO body) {
        return restClient.post(CREATE_USER_PATH, body, GenericErrorResponse.class);
    }

    @Override
    public ResponseEntity<Void> deleteUser(final Long userId) {
        String endpoint = StringUtils
                .replace(DELETE_USER_PATH, "{userId}", String.valueOf(userId));

        return restClient.delete(endpoint, Void.class);
    }

    @Override
    public ResponseEntity<GenericErrorResponse> deleteUserNeg(final Long userId) {
        String endpoint = StringUtils
                .replace(DELETE_USER_PATH, "{userId}", String.valueOf(userId));

        return restClient.delete(endpoint, GenericErrorResponse.class);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        return restClient.getList(GET_USER_PATH, UserDTO.class);
    }

    @Override
    public ResponseEntity<GenericErrorResponse> getUsersNeg() {
        return restClient.get(GET_USER_PATH, GenericErrorResponse.class);
    }
}
