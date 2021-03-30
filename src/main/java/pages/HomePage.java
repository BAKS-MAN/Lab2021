package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    private static final String homePageUrl = "http://localhost:8080/";

    @FindBy(xpath = "//div[contains(@class, 'loginForm__login')]//input")
    private WebElement loginInputField;

    @FindBy(xpath = "//div[contains(@class, 'loginForm__password')]//input")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'userBlock__menu-icon')]")
    private WebElement userBlockMenu;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(@href, '/settings')]")
    private WebElement settingIcon;

    public void openHomePage() {
        openUrl(homePageUrl);
    }

    public void enterLoginData(String login, String password) {
        waitForElementPresent(loginInputField, "login form is not displayed", 3);
        loginInputField.sendKeys(login);
        passwordInputField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public boolean isUserBlockDisplayed() {
        waitUntil(() -> isElementDisplayed(userBlockMenu), 5, 1);
        return isElementDisplayed(userBlockMenu);
    }

    public void openUserBlockMenu() {
        userBlockMenu.click();
    }

    public void goToSettings() {
        settingIcon.click();
    }
}
