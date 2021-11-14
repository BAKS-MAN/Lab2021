package org.epam.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import org.epam.util.ReportportalConfig;
import org.epam.util.RestApiConfig;
import org.epam.util.SlackService;

public class CucumberHooks {
    private String testName;

    @Before
    public void runRestAssuredConfig() {
        RestApiConfig.getInstance();
        ReportportalConfig.getInstance();
    }

    @Before(order = 10)
    public void sendSlackStartNotification(Scenario scenario) {
        testName = scenario.getName();
        SlackService.postNotification(String.format("Test '%s' execution has been started!", testName));
    }

    @After
    public void tearDown(Scenario scenario) {
        testName = scenario.getName();
        SlackService.postNotification(String.format("Test '%s' execution has been finished!", testName));
    }

    @After(value = "@CloseBrowser", order = 0)
    public void closeBrowser() {
        Serenity.getWebdriverManager().closeDriver();
    }
}
