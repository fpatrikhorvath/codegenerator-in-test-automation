package com.automation.regression.stepdef;

import com.automation.regression.TestCore;
import com.automation.regression.context.ScenarioContext;
import com.automation.regression.rest.model.ContextUser;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.model.CreateUser201Response;
import org.openapitools.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.testng.AssertJUnit.*;

public class UserSteps extends TestCore {
    //private static final Logger LOG = LogManager.getLogger(UserSteps.class);

    public UserSteps(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @Given("(create )a new user of status {word} and store it as {word} -> {}")
    public void createANewUserOfTypeAndStoreItAs
            (final String statusString, final String contextId, final HttpStatus httpStatus) {

        ContextUser user = getUserService().initContextUser(contextId, statusString);

        ResponseEntity<CreateUser201Response> response = getUserService().registerUser(user);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(httpStatus));

        if (response.getStatusCode().isSameCodeAs(HttpStatus.CREATED)) {
            user.setId(Objects.requireNonNull(response.getBody()).getId());
        }

        scenarioContext.storeContextObject(contextId, user);
    }

    @Then("verify that user {word} exists")
    public void verifyThatUserExists(final String contextId) {
        ContextUser expUser = (ContextUser) scenarioContext.getContextObject(contextId);

        ResponseEntity<List<User>> response = getUserService().getUsers();
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        User actUser = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(u -> Objects.equals(u.getName(), expUser.getName()))
                .findFirst()
                .orElse(null);

        getUserVerifyService().verifyUser(expUser, actUser);
    }

    @When("delete user {word} -> {}")
    public void deleteUser(final String contextId, final HttpStatus httpStatus) {
        ContextUser user = (ContextUser) scenarioContext.getContextObject(contextId);
        ResponseEntity<Void> response = getUserService().deleteUser(user.getId());
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(httpStatus));
    }

    @Then("verify that user {word} does not exist")
    public void verifyThatUserDoesNotExist(final String contextId) {
        ContextUser user = (ContextUser) scenarioContext.getContextObject(contextId);

        ResponseEntity<List<User>> response = getUserService().getUsers();
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        User actUser = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(u -> Objects.equals(u.getName(), user.getName()))
                .findFirst()
                .orElse(null);

        assertNull(actUser);
    }

}
