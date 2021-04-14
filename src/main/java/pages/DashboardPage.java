package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.epam.data.dto.DataStorage;
import org.openqa.selenium.By;

public class DashboardPage extends AbstractPage {

    DataStorage dataStorage = DataStorage.getInstance();

    @FindBy(xpath = "//div[contains(@class, 'addDashboardButton')]/button")
    private WebElementFacade addNewDashboardButton;

    @FindBy(xpath = "//form[contains(@class, 'add-dashboard-form')]//input")
    private WebElementFacade addDashboardModalNameInput;

    @FindBy(xpath = "//button[.='Edit']")
    private WebElementFacade editButton;

    @FindBy(xpath = "//div[contains(@class,'buttons-block')]//span[.='Delete']")
    private WebElementFacade deleteButton;

    @FindBy(xpath = "//div[contains(@class, 'modalFooter')]//button[.='Delete']")
    private WebElementFacade deleteDashboardModalButton;

    private static final String ADD_BUTTON = "Add";
    private static final String UPDATE_BUTTON = "Update";
    private String dashboardModalActionButton = "//div[contains(@class, 'modalFooter')]/button[.='%s']";
    private String openedDashBoardTitle = "//ul[contains(@class, 'pageBreadcrumbs')]//span[.='%s']";


    public void addNewDashboard(String dashboardName) {
        addNewDashboardButton.click();
        setDashboardName(dashboardName);
        clickDashboardModalButton(ADD_BUTTON);
        dataStorage.setDashboardName(dashboardName);
    }

    public boolean isDashBoardPageDisplayed() {
        return isElementDisplayed(addNewDashboardButton, 5);
    }

    public boolean isAddedDashBoardDisplayed(String dashboardName) {
        return isElementDisplayed(getDashBoardTitleElement(dashboardName), 5);
    }

    private WebElementFacade getDashBoardTitleElement(String dashboardName) {
        return find(By.xpath(String.format(openedDashBoardTitle, dashboardName)));
    }

    public void deleteDshBoard() {
        deleteButton.click();
        waitForElementAndClick(deleteDashboardModalButton, 3);
    }

    private void clickEditButton() {
        editButton.click();
    }

    private void setDashboardName(String dashboardName) {
        waitForElementAndSendKeys(addDashboardModalNameInput, dashboardName, 5);
    }

    private void clickDashboardModalButton(String buttonName) {
        find(By.xpath(String.format(dashboardModalActionButton, buttonName))).click();
    }

    public void setNewDashboardName(String dashboardName) {
        clickEditButton();
        setDashboardName(dashboardName);
        clickDashboardModalButton(UPDATE_BUTTON);
    }
}
