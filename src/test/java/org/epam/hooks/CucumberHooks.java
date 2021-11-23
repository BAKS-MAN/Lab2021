package org.epam.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import org.epam.util.ReportportalConfig;
import org.epam.util.RestApiConfig;

public class CucumberHooks {
    @Before
    public void runRestAssuredConfig() {
        RestApiConfig.getInstance();
        ReportportalConfig.getInstance();
    }

    @After(value = "@CloseBrowser", order = 0)
    public void closeBrowser() {
        Serenity.getWebdriverManager().closeDriver();
    }
}
