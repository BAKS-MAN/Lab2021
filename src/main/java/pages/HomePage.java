package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    public HomePage(WebDriver driver) {
        super((driver));
    }

    private static final String homePageUrl = "http://localhost:8080/";

    @FindBy(xpath = "//div[contains(@class, 'loginForm__login')]//input")
    private WebElement loginInputField;

    @FindBy(xpath = "//div[contains(@class, 'loginForm__password')]//input")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;


    public void openHomePage() {
        openUrl(homePageUrl);
    }

    public void login(String login, String password) {
        waitForElementPresent(loginInputField, "login form is not displayed", 3);
        loginInputField.sendKeys(login);
        passwordInputField.sendKeys(password);
        loginButton.click();
    }
}
