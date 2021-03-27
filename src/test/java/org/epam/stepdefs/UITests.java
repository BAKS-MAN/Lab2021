package org.epam.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.epam.data.dto.UserDTO;
import org.epam.data.user.UserFactory;
import org.epam.hooks.WebDriverProvider;
import org.junit.jupiter.api.Assertions;
import pages.DashboardPage;
import pages.HomePage;

import static org.epam.common.DateTimeUtil.getCurrentDateWithTime;

@Slf4j
public class UITests {

    HomePage homePage = new HomePage(WebDriverProvider.getDriver());
    DashboardPage dashboardPage = new DashboardPage(WebDriverProvider.getDriver());

    @Given("^open reportportal main page$")
    public void openMainPage() {
        homePage.openHomePage();
    }

    @Given("^login as (regular user|admin)$")
    public void loginAsUser(String userType) {
        UserDTO user = UserFactory.getUser(userType);
        homePage.login(user.getLogin(), user.getPassword());
        log.info("User is logged in as " + userType);
    }

    @Then("^dashboard page is open$")
    public void dashboardPageIsOpen() {
        Assertions.assertTrue(dashboardPage.isDashBoardPageDisplayed(), "dashboard page is not opened");
    }

    @When("^add new dashboard$")
    public void addNewDashboard() {
        String dashboardName = getCurrentDateWithTime("dd-MM-yy-HHmm");
        dashboardPage.addNewDashboard(dashboardName);
        Assertions.assertTrue(
                dashboardPage.isAddedDashBoardDisplayed(dashboardName), "dashboard page is not added");
    }

    @When("^delete added dashboard$")
    public void deleteAddedDashboard() {
        dashboardPage.deleteDshBoard();
    }
}
