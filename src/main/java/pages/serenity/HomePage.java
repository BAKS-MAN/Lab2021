package pages.serenity;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.epam.util.ConfigDataReader.getConfigData;
import static org.epam.util.ConfigurationConstants.BASE_URI;

public class HomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = getConfigData(BASE_URI);

    @FindBy(css = "div[class*='loginForm__login'] input")
    private WebElementFacade loginInputField;

    @FindBy(css = "div[class*='loginForm__password'] input")
    private WebElementFacade passwordInputField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade loginButton;

    @FindBy(css = "div[class*='user-block']")
    private WebElementFacade userBlockMenu;

    @FindBy(className = "notification-transition-enter-done")
    private WebElementFacade loginNotification;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElementFacade logoutButton;

    @FindBy(css = "a[href$= '/settings']")
    private WebElementFacade settingIcon;

    @FindBy(css = "a[href$= '/launches']")
    private WebElementFacade launches;

    public void openHomePage() {
        openUrl(HOMEPAGE_URL);
    }

    public void enterLoginData(String login, String password) {
        waitForElementPresent(loginInputField, "login form is not displayed", 5);
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
        fluentWaitUntilElementPresent(userBlockMenu, 5);
        return userBlockMenu.isCurrentlyVisible();
    }

    public void openUserBlockMenu() {
        userBlockMenu.click();
    }

    public void goToSettings() {
        settingIcon.click();
    }

    public void goToLaunches() {
        launches.click();
    }

    public void waitForLoginNotificationDisappear() {
        //because sometimes notification appears after 1 sec.
        fluentWaitUntilElementPresent(loginNotification, 1);
        waitUntilElementDisappear(loginNotification, 10);
    }
}
