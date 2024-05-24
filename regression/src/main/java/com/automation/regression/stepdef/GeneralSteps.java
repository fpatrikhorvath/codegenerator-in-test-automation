package com.automation.regression.stepdef;

import com.automation.regression.context.ScenarioContext;
import com.automation.regression.rest.ResponseErrorEnum;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;

import static org.testng.AssertJUnit.assertEquals;

public class GeneralSteps extends TestCore {
    public GeneralSteps(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @Then("the response has {} error")
    public void theResponseHasError(final ResponseErrorEnum expectedResponseMessage) {
        ResponseErrorEnum actualResponseMessage = scenarioContext.getResponse();
        assertEquals(expectedResponseMessage, actualResponseMessage);
    }
}
