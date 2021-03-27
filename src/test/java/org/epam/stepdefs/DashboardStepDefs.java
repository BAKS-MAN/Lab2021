package org.epam.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashboardPage;

import static org.epam.util.DateTimeUtil.getCurrentDateWithTime;

public class DashboardStepDefs {

    DashboardPage dashboardPage;

    @Then("^dashboard page is open$")
    public void dashboardPageIsOpen() {
        Assert.assertTrue("dashboard page is opened", dashboardPage.isDashBoardPageDisplayed());
    }

    @When("^add new dashboard$")
    public void addNewDashboard() {
        String dashboardName = getCurrentDateWithTime("dd-MM-yy-HHmm");
        dashboardPage.addNewDashboard(dashboardName);
        Assert.assertTrue("dashboard page is added", dashboardPage.isAddedDashBoardDisplayed(dashboardName));
    }

    @When("^delete added dashboard$")
    public void deleteAddedDashboard() {
        dashboardPage.deleteDshBoard();
    }
}
