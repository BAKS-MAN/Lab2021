package org.epam.tests.selenium;


import io.github.bonigarcia.seljup.Arguments;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.epam.steps.selenium.DashboardPageSteps;
import org.epam.steps.selenium.HomePageSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.epam.util.ConfigurationConstants.*;

class DashboardActionsTest {

    @RegisterExtension
    static SeleniumJupiter seleniumJupiter = new SeleniumJupiter();

    @BeforeAll
    static void setup() {
        seleniumJupiter.getConfig().takeScreenshotAsPng();
        seleniumJupiter.getConfig().setOutputFolder(LOCAL_FOLDER_TO_STORE_FILES);
        seleniumJupiter.getConfig().enableScreenshotWhenFailure();
    }

    @Test
    void resizeDashboardWidgetToHalfSize(
            @DriverUrl("http://localhost:4444/wd/hub")
            @Arguments({"--incognito"})
            @DriverCapabilities({"browserName=chrome", "version=93.0"}) RemoteWebDriver driver) {
        System.setProperty(ENVIRONMENT, LOCAL_DOCKER_ENVIRONMENT);
        DashboardPageSteps dashboardPageSteps = new DashboardPageSteps(driver);
        navigateToDashboardWithDemoWidgets(driver);
        dashboardPageSteps.resizeFirstWidgetToMinimalScreenWidth();
        dashboardPageSteps.checkFirstWidgetContentAfterResizeToHalfScreenWidth();
    }

    @Test
    void resizeDashboardWidgetToHalfSizeLocal(ChromeDriver driver) {
        DashboardPageSteps dashboardPageSteps = new DashboardPageSteps(driver);
        navigateToDashboardWithDemoWidgets(driver);
        dashboardPageSteps.resizeFirstWidgetToMinimalScreenWidth();
        dashboardPageSteps.checkFirstWidgetContentAfterResizeToHalfScreenWidth();
    }

    @Test
    void resizeDashboardWidgetToFullSize(ChromeDriver driver) {
        DashboardPageSteps dashboardPageSteps = new DashboardPageSteps(driver);
        navigateToDashboardWithDemoWidgets(driver);
        dashboardPageSteps.checkSecondWidgetLocationAfterFirstWidgetResize();
        dashboardPageSteps.resizeFirstWidgetToMinimalScreenWidth();
    }

    @Test
    void resizeDashboardWidgetOverSize(ChromeDriver driver) {
        DashboardPageSteps dashboardPageSteps = new DashboardPageSteps(driver);
        navigateToDashboardWithDemoWidgets(driver);
        dashboardPageSteps.resizeLastWidgetWiderThenDashboard();
    }

    private void navigateToDashboardWithDemoWidgets(RemoteWebDriver driver) {
        HomePageSteps homePageSteps = new HomePageSteps(driver);
        // via driver, cause chrome capability '--start-maximized' doesn't work
        driver.manage().window().maximize();
        homePageSteps.loginAsRegularUser();
        homePageSteps.checkUserIsLoggedIn();
        homePageSteps.goToDashboardsPage();
        DashboardPageSteps dashboardPageSteps = new DashboardPageSteps(driver);
        dashboardPageSteps.dashboardPageIsOpen();
        dashboardPageSteps.openDemoDashboard();
        dashboardPageSteps.demoWidgetsAreDisplayed();
    }
}
