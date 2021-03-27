package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class SettingsPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class, 'navigationTabs')]/a[contains(@href, 'general')]")
    private WebElementFacade generalSettingTab;

    private String dropdown = "//span[.='%s']/..//div[contains(@class, 'dropdown-container')]";

    private String dropdownSelectedValue = "//span[contains(@class, 'inputDropdown__value')]";

    @FindBy(xpath = "//div[contains(@class, 'inputDropdown__opened')]//div[contains(@class, 'single-option')]")
    private List<WebElementFacade> dropdownOptions;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//p[.='Project settings were successfully updated']")
    private WebElementFacade successfulNotification;


    public boolean isSettingsPageDisplayed() {
        waitUntil(() -> isElementDisplayed(generalSettingTab), 5, 1);
        return isElementDisplayed(generalSettingTab);
    }

    private void openDropDown(String settingName) {
        find(By.xpath(String.format(dropdown, settingName))).click();
    }

    public String getDropdownSelectedValue(String settingName) {
        return find(By.xpath(String.format(dropdown, settingName) + dropdownSelectedValue)).getText();
    }

    public void changeSettingToRandomValue(String settingName) {
        openDropDown(settingName);
        String currentValue = getDropdownSelectedValue(settingName);
        dropdownOptions.stream().filter(webElement -> !webElement.getText().equals(currentValue))
                .findAny().get().click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public boolean isSuccessfulNotificationDisplayed() {
        waitUntil(() -> isElementDisplayed(successfulNotification), 3, 1);
        return isElementDisplayed(successfulNotification);
    }
}
