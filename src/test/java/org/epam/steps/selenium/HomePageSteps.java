package org.epam.steps.selenium;

import com.epam.reportportal.annotations.Step;
import lombok.extern.slf4j.Slf4j;
import org.epam.data.dto.UserDTO;
import org.epam.data.user.UserFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.selenium.HomePage;

import static org.epam.util.TestDataConstants.REGULAR_USER;

@Slf4j
public class HomePageSteps {

    HomePage homePage;

    public HomePageSteps(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    @Step("open Home Page")
    public void openHomePage() {
        homePage.openHomePage();
    }

    @Step("login as regular user")
    public void loginAsRegularUser() {
        homePage.openHomePage();
        UserDTO user = UserFactory.getUser(REGULAR_USER);
        homePage.enterLoginData(user.getLogin(), user.getPassword());
        homePage.clickLoginButton();
        log.info("User is logged in as regular user");
    }

    @Step("check that user is logged in")
    public void checkUserIsLoggedIn() {
        Assert.assertTrue("user is logged in", homePage.isUserBlockDisplayed());
        homePage.waitForLoginNotificationDisappear();
    }

    @Step("go to Launches page")
    public void goToLaunchesPage() {
        homePage.goToLaunches();
    }

    @Step("go to Dashboards page")
    public void goToDashboardsPage() {
        homePage.goToDashboards();
    }
}
