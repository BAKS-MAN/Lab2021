package org.epam.stepdefs.serenity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.epam.data.dto.UserDTO;
import org.epam.data.user.UserFactory;
import org.junit.Assert;
import pages.serenity.HomePage;

@Slf4j
public class HomePageStepDefs {

    HomePage homePage;

    @Given("^open reportportal main page$")
    public void openMainPage() {
        homePage.openHomePage();
    }

    @Given("^login as (regular user|admin)$")
    public void loginAsUser(String userType) {
        UserDTO user = UserFactory.getUser(userType);
        homePage.enterLoginData(user.getLogin(), user.getPassword());
        homePage.clickLoginButton();
        log.info("User is logged in as " + userType);
    }

    @Then("^user is logged in$")
    public void userIsLoggedIn() {
        Assert.assertTrue("user is not logged in", homePage.isUserBlockDisplayed());
        homePage.waitForLoginNotificationDisappear();
    }

    @When("logout")
    public void logout() {
        homePage.openUserBlockMenu();
        homePage.clickLogoutButton();
    }

    @Then("^user is not logged in$")
    public void userIsNotLoggedIn() {
        Assert.assertFalse("user is not logged in", homePage.isUserBlockDisplayed());
    }

    @When("^navigate to settings page$")
    public void navigateToSettingsPage() {
        homePage.goToSettings();
    }
}
