package pages;

import com.google.common.base.Stopwatch;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.BooleanSupplier;

import static java.util.concurrent.TimeUnit.SECONDS;

public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected WebElement waitForElementPresent(WebElement webElement, String errorMessage, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).withMessage(errorMessage)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitForElementPresent(WebElement webElement, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).withMessage("element: '" + webElement + "' was not found")
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForElementAndClick(WebElement webElement, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(webElement, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(WebElement webElement, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(webElement, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void waitUntil(final BooleanSupplier condition,
                                 final long waitTimeoutSec, final long waitIntervalSec) {
        final Stopwatch waitTimer = Stopwatch.createStarted();
        while (waitTimer.elapsed(SECONDS) < waitTimeoutSec) {
            try {
                Thread.sleep(waitIntervalSec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (condition.getAsBoolean()) {
                waitTimer.stop();
                return;
            }
        }
        waitTimer.stop();
    }
}
