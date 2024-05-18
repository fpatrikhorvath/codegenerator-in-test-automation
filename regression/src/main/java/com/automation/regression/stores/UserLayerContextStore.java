package com.automation.regression.stores;

import com.automation.regression.service.IUserService;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserLayerContextStore {
    private final IUserService userService;

    public UserLayerContextStore(final IUserService userService) {
        this.userService = userService;
    }

    public IUserService getUserService() {
        return userService;
    }
}
