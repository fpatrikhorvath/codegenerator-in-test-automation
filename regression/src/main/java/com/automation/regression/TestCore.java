package com.automation.regression;

import com.automation.regression.service.IUserService;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.spring.ScenarioScope;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;


@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class, value = {"classpath:spring.xml"})
@SpringBootTest(classes = TestCore.class)
@Primary
@ScenarioScope
public class TestCore {
    protected final String responseCodeCheckMessage = "Expected response code (%d) does not match with actual (%d)";
    private final UserLayerContextStore userLayerContextStore;
    public TestCore(final UserLayerContextStore userLayerContextStore) {
        this.userLayerContextStore = userLayerContextStore;
    }

    protected IUserService getUserService() {
        return userLayerContextStore.getUserService();
    }
}