package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.epam.util.ConfigDataReader.getConfigData;
import static org.epam.util.ConfigurationConstants.BASE_URI;

public class HomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = getConfigData(BASE_URI);

    @FindBy(xpath = "//div[contains(@class, 'loginForm__login')]//input")
    private WebElementFacade loginInputField;

    @FindBy(xpath = "//div[contains(@class, 'loginForm__password')]//input")
    private WebElementFacade passwordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade loginButton;

    @FindBy(xpath = "//div[contains(@class, 'userBlock__menu-icon')]")
    private WebElementFacade userBlockMenu;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElementFacade logoutButton;

    @FindBy(xpath = "//a[contains(@href, '/settings')]")
    private WebElementFacade settingIcon;

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
        return isElementDisplayed(userBlockMenu, 5);
    }

    public void openUserBlockMenu() {
        userBlockMenu.click();
    }

    public void goToSettings() {
        settingIcon.click();
    }
}
