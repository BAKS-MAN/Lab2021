package org.epam.steps;

import net.thucydides.core.annotations.Step;
import org.epam.data.dto.UserDTO;
import org.epam.data.user.UserFactory;
import org.junit.Assert;
import pages.HomePage;

import static org.epam.util.TestDataConstants.REGULAR_USER;

public class HomePageSteps {

    HomePage homePage;

    @Step("login as regular user")
    public void loginAsRegularUser() {
        homePage.openHomePage();
        UserDTO user = UserFactory.getUser(REGULAR_USER);
        homePage.enterLoginData(user.getLogin(), user.getPassword());
        homePage.clickLoginButton();
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
}
