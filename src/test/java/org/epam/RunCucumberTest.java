package org.epam;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.epam.common.RestApiConfig;
import org.epam.hooks.WebDriverProvider;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        glue = {
                "com/epam/stepdefs",
                "org/epam/hooks"},
        stepNotifications = true
)
public class RunCucumberTest {
    @Before
    public void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverProvider.setDriver(driver);
    }

    @Before
    public void runRestAssuredConfig() {
        RestApiConfig.getInstance();
    }

    @After
    public void closeDriver() {
        WebDriverProvider.getDriver().close();
    }
}
