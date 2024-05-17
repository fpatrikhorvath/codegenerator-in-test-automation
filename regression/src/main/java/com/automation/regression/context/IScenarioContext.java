package com.automation.regression.context;

public interface IScenarioContext {
    void storeContextObject(String key, Object object);

    Object getContextObject(String key);
}
