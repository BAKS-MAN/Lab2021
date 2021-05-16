package org.epam.hooks;

import io.cucumber.java.Before;
import org.epam.util.ReportportalConfig;
import org.epam.util.RestApiConfig;

public class CucumberHooks {
    @Before
    public void runRestAssuredConfig() {
        RestApiConfig.getInstance();
        ReportportalConfig.getInstance();
    }
}
