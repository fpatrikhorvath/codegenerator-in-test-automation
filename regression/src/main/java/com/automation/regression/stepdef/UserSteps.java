package com.automation.regression.stepdef;

import com.automation.regression.TestCore;
import com.automation.regression.rest.model.ContextUser;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.model.CreateUser201Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.testng.AssertJUnit.assertTrue;

public class UserSteps extends TestCore {
    private static final Logger logger = LogManager.getLogger(UserSteps.class);

    public UserSteps(UserLayerContextStore userLayerContextStore) {
        super(userLayerContextStore);
    }


    @Given("create a new user of status {word} and store it as {word} - {}")
    public void createANewUserOfTypeAndStoreItAs(final String statusString, final String contextId, final HttpStatus httpStatus) {
        ContextUser user = getUserService().initContextUser(contextId, statusString);
        ResponseEntity<CreateUser201Response> response = getUserService().registerUser(user);
        assertTrue(responseCodeCheckMessage, response.getStatusCode().isSameCodeAs(httpStatus));
    }
}
