package pages.serenity;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Slf4j
public class AbstractPage extends PageObject {

    protected WebElement waitForElementPresent(WebElement webElement, String errorMessage, long timeOutInSeconds) {
        return new WebDriverWait(getDriver(), timeOutInSeconds).withMessage(errorMessage)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitForElementPresent(WebElement webElement, long timeOutInSeconds) {
        return new WebDriverWait(getDriver(), timeOutInSeconds)
                .withMessage("element: '" + webElement + "' was not found")
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

    protected void fluentWaitUntilElementPresent(WebElementFacade webElement, int waitTimeoutSec) {
        try {
            waitForCondition().withTimeout(Duration.ofSeconds(waitTimeoutSec)).pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException | NoSuchElementException e) {
            log.debug(String.format("element: '%s' was not appeared after %d seconds", webElement, waitTimeoutSec));
        }
    }

    protected void waitUntilElementDisappear(WebElementFacade webElement, int waitTimeoutSec) {
        webElement.withTimeoutOf(Duration.ofSeconds(waitTimeoutSec)).waitUntilNotVisible();
    }

    protected String getTextFromWebElement(WebElementFacade webElementFacade) {
        return webElementFacade.getTextContent();
    }
}
