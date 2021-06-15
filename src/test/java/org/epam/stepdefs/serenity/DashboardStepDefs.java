package org.epam.stepdefs.serenity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.serenity.DashboardPage;

import static org.epam.util.DateTimeUtil.getCurrentDateWithTime;

public class DashboardStepDefs {

    DashboardPage dashboardPage;

    @Then("^dashboard page is open$")
    public void dashboardPageIsOpen() {
        Assert.assertTrue("dashboard page is opened", dashboardPage.isDashBoardPageDisplayed());
    }

    @When("^add new dashboard$")
    public void addNewDashboard() {
        String dashboardName = generateDashboardName();
        dashboardPage.addNewDashboard(dashboardName);
        Assert.assertTrue("dashboard page is added", dashboardPage.isAddedDashBoardDisplayed(dashboardName));
    }

    @When("^edit dashboard$")
    public void editDashboard() {
        dashboardPage.setNewDashboardName(generateDashboardName());
    }

    @When("^delete added dashboard$")
    public void deleteAddedDashboard() {
        dashboardPage.deleteDshBoard();
    }

    private String generateDashboardName() {
        return getCurrentDateWithTime("dd-MM-yy-HHmm");
    }
}
