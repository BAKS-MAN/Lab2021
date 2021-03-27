package pages;

import org.epam.data.dto.DataStorage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractPage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    DataStorage dataStorage = DataStorage.getInstance();

    @FindBy(xpath = "//div[contains(@class, 'addDashboardButton')]/button")
    private WebElement addNewDashboardButton;

    @FindBy(xpath = "//form[contains(@class, 'add-dashboard-form')]//input")
    private WebElement addDashboardModalNameInput;

    @FindBy(xpath = "//div[contains(@class, 'modalFooter')]/button[.='Add']")
    private WebElement addDashboardModalButton;

    @FindBy(xpath = "//div[contains(@class,'buttons-block')]//span[.='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[contains(@class, 'modalFooter')]//button[.='Delete']")
    private WebElement deleteDashboardModalButton;

    private String openedDashBoardTitle = "//ul[contains(@class, 'pageBreadcrumbs')]//span[.='%s']";


    public void addNewDashboard(String dashboardName) {
        addNewDashboardButton.click();
        waitForElementAndSendKeys(addDashboardModalNameInput, dashboardName, 5);
        addDashboardModalButton.click();
        waitUntil(() -> isAddedDashBoardDisplayed(dashboardName), 5, 1);
        dataStorage.setDashboardName(dashboardName);
    }

    public boolean isDashBoardPageDisplayed() {
        waitUntil(() -> isElementDisplayed(addNewDashboardButton), 5, 1);
        return isElementDisplayed(addNewDashboardButton);
    }

    public boolean isAddedDashBoardDisplayed(String dashboardName) {
        WebElement element = driver.findElement(By.xpath(String.format(openedDashBoardTitle, dashboardName)));
        return isElementDisplayed(element);
    }

    public void deleteDshBoard() {
        deleteButton.click();
        waitForElementAndClick(deleteDashboardModalButton, 3);
    }

}
