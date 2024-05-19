package com.automation.regression.stores;

import com.automation.regression.service.UserService;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserLayerContextStore {
    private final UserService userService;

    public UserLayerContextStore(final UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
