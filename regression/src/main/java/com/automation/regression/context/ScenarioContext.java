package com.automation.regression.context;

import io.cucumber.spring.ScenarioScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@ScenarioScope
public class ScenarioContext{
    private final HashMap<String, Object> contextObjectMap = new HashMap<>();

    public ScenarioContext() {
    }

    public void storeContextObject(final String key, final Object object) {
        contextObjectMap.put(key, object);
    }

    public Object getContextObject(final String key) {
        return contextObjectMap.get(key);
    }

}
