package pages.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.epam.util.ConfigDataReader.getConfigData;
import static org.epam.util.ConfigurationConstants.BASE_URI;

@Slf4j
public class HomePage extends AbstractPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static final String HOMEPAGE_URL = getConfigData(BASE_URI);

    @FindBy(css = "div[class*='loginForm__login'] input")
    private WebElement loginInputField;

    @FindBy(css = "div[class*='loginForm__password'] input")
    private WebElement passwordInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "div[class*='user-block']")
    private WebElement userBlockMenu;

    @FindBy(className = "notification-transition-enter-done")
    private WebElement loginNotification;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutButton;

    @FindBy(css = "a[href$= '/settings']")
    private WebElement settingSidebarButton;

    @FindBy(css = "a[href$= '/launches']")
    private WebElement launchesSidebarButton;

    @FindBy(css = "div[class*='sidebarButton'] a[href$= '/dashboard']")
    private WebElement dashboardsSidebarButton;

    public void openHomePage() {
        openUrl(HOMEPAGE_URL);
        log.info("Open home page with URL: " + HOMEPAGE_URL);
    }

    public void enterLoginData(String login, String password) {
        waitForElementPresent(loginInputField, "login form is not displayed", 5);
        loginInputField.sendKeys(login);
        log.info("Enter user login: " + login);
        passwordInputField.sendKeys(password);
        log.info("Enter user password: ***");
    }

    public void clickLoginButton() {
        loginButton.click();
        log.info("Click login button");
    }

    public void clickLogoutButton() {
        logoutButton.click();
        log.info("Click logout button");
    }

    public boolean isUserBlockDisplayed() {
        return isElementDisplayed(fluentWaitUntilElementPresent(userBlockMenu, 5));
    }

    public void openUserBlockMenu() {
        userBlockMenu.click();
        log.info("Click User block menu");
    }

    public void goToSettings() {
        settingSidebarButton.click();
        log.info("Click settings button");
    }

    public void goToLaunches() {
        launchesSidebarButton.click();
        log.info("Click 'Launches' button");
    }

    public void goToDashboards() {
        dashboardsSidebarButton.click();
        log.info("Click 'Dashboard' button");
    }

    public void waitForLoginNotificationDisappear() {
        waitUntilElementDisappear(loginNotification, 10);
    }
}
