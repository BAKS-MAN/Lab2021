package pages.selenium;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.epam.util.DateTimeUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.epam.util.ConfigurationConstants.LOCAL_FOLDER_TO_STORE_FILES;

@Slf4j
public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String JS_SCROLL_TO_ELEMENT = "arguments[0].scrollIntoView(false);";
    private static final String JS_CLICK = "arguments[0].click();";

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected WebElement waitForElementPresent(WebElement webElement, String errorMessage, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).withMessage(errorMessage)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitForElementPresent(WebElement webElement, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds)
                .withMessage("element: '" + webElement + "' was not found")
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementAndClick(WebElement webElement, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(webElement, timeoutInSeconds);
        element.click();
    }

    public void waitForElementAndSendKeys(WebElement webElement, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(webElement, timeoutInSeconds);
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayedByLocator(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void waitUntilElementDisappear(WebElement webElement, int waitTimeoutSec) {
        ExpectedCondition<Boolean> elementIsDisplayed = arg0 -> {
            try {
                webElement.isDisplayed();
                return false;
            } catch (NoSuchElementException e) {
                return true;
            }
        };
        new WebDriverWait(driver, waitTimeoutSec).until(elementIsDisplayed);
    }

    public WebElement fluentWaitUntilElementPresent(WebElement webElement, int waitTimeoutSec) {
        try {
            getFluentWait(waitTimeoutSec)
                    .until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
        } catch (TimeoutException e) {
            log.info("WebElement: {} was not displayed after {} seconds.", webElement.toString(), waitTimeoutSec);
        }
        return webElement;
    }

    public List<WebElement> fluentWaitUntilElementsPresent(List<WebElement> webElements, int waitTimeoutSec) {
        try {
            getFluentWait(waitTimeoutSec)
                    .until(ExpectedConditions.visibilityOfAllElements(webElements));
        } catch (TimeoutException e) {
            log.info("WebElements: {} were not displayed after {} seconds.", webElements.toString(), waitTimeoutSec);
        }
        return webElements;
    }

    public FluentWait<WebDriver> getFluentWait(int waitTimeoutSec) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(waitTimeoutSec))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    //JS
    public void scrollElementIntoView(WebElement element) {
        getJavascriptExecutor().executeScript(JS_SCROLL_TO_ELEMENT, element);
    }

    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) driver;
    }

    public void takeScreenShot() {
        TakesScreenshot screenShot = ((TakesScreenshot) driver);
        String fileName = String.format("Screenshot_%s.png", DateTimeUtil.generateTimestamp());
        File screenShotFile = screenShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile, new File(LOCAL_FOLDER_TO_STORE_FILES + fileName));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
