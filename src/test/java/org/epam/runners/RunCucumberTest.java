package org.epam.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"pretty"},
        glue = {
                "org/epam/stepdefs",
                "org/epam/hooks"},
        stepNotifications = true,
        monochrome = true
)
public class RunCucumberTest {
}
