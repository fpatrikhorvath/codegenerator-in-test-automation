package com.automation.regression.service;

import com.automation.regression.rest.model.ContextUser;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.User;
import org.springframework.stereotype.Service;


import static org.testng.AssertJUnit.assertEquals;

@Service
@ScenarioScope
public class UserVerifyService {
    private static final Logger logger = LogManager.getLogger(UserVerifyService.class);

    public void verifyUser(final ContextUser expUser, final User actUser) {
        assertEquals("Expected user id is not matching with the actual", actUser.getId(), expUser.getId());
        assertEquals("Expected user name is not matching with the actual", actUser.getName(), expUser.getName());
        assertEquals("Expected user email is not matching with the actual", actUser.getEmail(), expUser.getEmail());
        assertEquals("Expected user status is not matching with the actual", actUser.getStatus(), expUser.getStatus());
    }
}
