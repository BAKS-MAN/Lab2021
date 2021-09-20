package pages.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import static org.epam.util.DashboardScreenSize.FULL_SCREEN_WIDTH;

@Slf4j
public class DashboardPage extends AbstractPage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    String getElementHorizontalMarginJScript = "const myEl = arguments[0];\n" +
            "return myEl.computedStyleMap().get('transform')[0].x.value;";

    @FindBy(css = "div[class*='addDashboardButton'] button")
    private WebElement addNewDashboardButton;

    @FindBy(css = "ul[class*='pageBreadcrumbs'] span")
    private WebElement openedDashBoardTitle;

    @FindBy(className = "widgets-grid")
    private WebElement widgetsContainer;

    @FindBy(css = "div[class*='layout__scrolling-content']")
    private WebElement widgetsPageLayout;

    @FindBy(css = "span[class*='react-resizable']")
    private List<WebElement> widgetResizeIcons;

    @FindBy(css = "div[class*='widgetsGrid__widget']")
    private List<WebElement> widgets;

    @FindBy(className = "c3-event-rects")
    private List<WebElement> widgetStatisticsChart;

    private String specifiedDashboardTemplate = "//div[contains(@class, 'gridRow')]/a[contains(" +
            "translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')]";

    public boolean isDashboardsPageDisplayed() {
        return isElementDisplayed(fluentWaitUntilElementPresent(addNewDashboardButton, 5));
    }

    public boolean isSpecifiedByNameDashboardDisplayed(String dashboardName) {
        return isElementDisplayedByLocator(getSpecifiedByNameDashboardLocator(dashboardName));
    }

    public void openSpecifiedByNameDashboard(String dashboardName) {
        WebElement dashboard = driver.findElement(getSpecifiedByNameDashboardLocator(dashboardName));
        log.info("Open '{}' dashboard", dashboard.getText());
        dashboard.click();
    }

    private By getSpecifiedByNameDashboardLocator(String dashboardName) {
        return By.xpath(String.format(specifiedDashboardTemplate, dashboardName.toLowerCase()));
    }

    public boolean isDashBoardOpened(String dashboardName) {
        fluentWaitUntilElementPresent(openedDashBoardTitle, 7);
        return openedDashBoardTitle.getText().toLowerCase().contains(dashboardName.toLowerCase());
    }

    public int resizeWidgetWidth(int widgetIndex, double resizeValue) {
        scrollElementIntoView(widgetResizeIcons.get(widgetIndex));
        int offsetValue = getOffsetValue(widgetIndex, getWidgetsContainerWidth(), resizeValue);
        resizeWidget(widgetIndex, offsetValue);
        int leftAndRightMarginValue = getMarginsForElement(widgets.get(widgetIndex), resizeValue);
        return getWidgetWidth(widgetIndex) + leftAndRightMarginValue;
    }

    public int resizeWidgetWiderThanWidgetContainer(int widgetIndex) {
        int widgetPageWidth = widgetsPageLayout.getSize().getWidth();
        int offsetValue = getOffsetValue(widgetIndex, widgetPageWidth, FULL_SCREEN_WIDTH.getValue());
        resizeWidget(widgetIndex, offsetValue);
        int leftAndRightMarginValue = getMarginsForElement(widgets.get(widgetIndex), FULL_SCREEN_WIDTH.getValue());
        return getWidgetWidth(widgetIndex) + leftAndRightMarginValue;
    }

    public void resizeWidget(int widgetIndex, int offsetValue) {
        Actions action = new Actions(driver);
        log.info("Performing resize action for widget");
        action.clickAndHold(widgetResizeIcons.get(widgetIndex))
                .moveByOffset(offsetValue, 0).release().build().perform();
    }

    private int getOffsetValue(int widgetIndex, int targetWidth, double resizeValue) {
        //to get offset between expected new width and current widget position
        int widgetRightBorderLocation = widgetResizeIcons.get(widgetIndex).getLocation().getX();
        return (int) (targetWidth * resizeValue) - widgetRightBorderLocation;
    }

    public int getWidgetsContainerWidth() {
        return widgetsContainer.getSize().getWidth();
    }

    public int getWidgetWidth(int widgetIndex) {
        return widgets.get(widgetIndex).getSize().getWidth();
    }

    private int getMarginsForElement(WebElement webElement, double resizeValue) {
        Long marginValue = (Long) getJavascriptExecutor().executeScript(getElementHorizontalMarginJScript, webElement);
        // if resize value is not full screen (1) then margin value for one element side is shared with another widget
        // So it's equal to half margin value (0.5)
        double marginMultiplier = resizeValue <= 0.5 ? 1.5 : 2;
        return (int) (marginValue * marginMultiplier);
    }

    public int getWidgetStatisticsChartWidth(int widgetIndex) {
        return widgetStatisticsChart.get(widgetIndex).getSize().getWidth();
    }

    public Point getWidgetLocation(int widgetIndex) {
        return widgetStatisticsChart.get(widgetIndex).getLocation();
    }

    public int getWidgetQuantity() {
        fluentWaitUntilElementsPresent(widgets, 5);
        return widgets.size();
    }
}
