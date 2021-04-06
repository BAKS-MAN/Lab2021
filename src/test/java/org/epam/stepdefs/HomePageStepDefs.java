package org.epam.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.epam.data.dto.UserDTO;
import org.epam.data.user.UserFactory;
import org.junit.Assert;
import pages.HomePage;

@Slf4j
public class HomePageStepDefs {

    HomePage homePage;

    @Before()
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

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
        Assert.assertTrue("user is logged in", homePage.isUserBlockDisplayed());
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
