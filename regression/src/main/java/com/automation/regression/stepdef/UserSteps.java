package com.automation.regression.stepdef;

import com.automation.regression.TestCore;
import com.automation.regression.context.IScenarioContext;
import com.automation.regression.rest.model.ContextUser;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class UserSteps extends TestCore {
    //private static final Logger LOG = LogManager.getLogger(UserSteps.class);

    public UserSteps(final UserLayerContextStore userLayerContextStore, final IScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @Given("(create )a new user of status {word} and store it as {word} -> {}")
    public void createANewUserOfTypeAndStoreItAs(final String statusString, final String contextId, final HttpStatus httpStatus) {
        ContextUser user = getUserService().initContextUser(contextId, statusString);

        ResponseEntity<CreateUser201Response> response = getUserService().registerUser(user);
        assertTrue(responseCodeCheckMessage, response.getStatusCode().isSameCodeAs(httpStatus));

        if (response.getStatusCode().isSameCodeAs(HttpStatus.CREATED)) {
            user.setId(Objects.requireNonNull(response.getBody()).getId());
        }

        scenarioContext.storeContextObject(contextId, user);
    }

    @Then("verify that {word} user exists")
    public void verifyThatUserExists(final String contextId) {
        ContextUser user = (ContextUser) scenarioContext.getContextObject(contextId);

        ResponseEntity<List<User>> response = getUserService().getUsers();
        assertTrue(responseCodeCheckMessage, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        User actUser = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(u -> Objects.equals(u.getName(), user.getName()))
                .findFirst()
                .orElse(null);
        //TODO: fix verification
        assertEquals(user, actUser);
    }
}
