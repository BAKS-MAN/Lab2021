package org.epam.stepdefs.serenity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.serenity.SettingsPage;

public class SettingsPageStepDefs {

    SettingsPage settingsPage;

    private static final String LAUNCH_INACTIVITY_TIMEOUT_SETTING = "Launch inactivity timeout";

    @Then("^settings page is open$")
    public void settingsPageIsOpen() {
        Assert.assertTrue("settings page is opened", settingsPage.isSettingsPageDisplayed());
    }

    @When("^update settings$")
    public void updateSettings() {
        settingsPage.changeSettingToRandomValue(LAUNCH_INACTIVITY_TIMEOUT_SETTING);
        settingsPage.clickSubmitButton();
    }

    @Then("^settings were updated$")
    public void settingsWereUpdated() {
        Assert.assertTrue("settings were updated", settingsPage.isSuccessfulNotificationDisplayed());
    }
}
