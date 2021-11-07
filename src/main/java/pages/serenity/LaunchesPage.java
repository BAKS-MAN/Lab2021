package pages.serenity;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

public class LaunchesPage extends AbstractPage {
    private By tableTitlesLocator = By.cssSelector("span[class*='headerCell__title-full']");
    private By testRunNameLocator = By.cssSelector("div[class*='itemInfo__main-info']");
    private By testRunLinkLocator = By.cssSelector("div[class*='itemInfo__main-info'] > a");
    private By testSuiteNameLocator = By.cssSelector("div[class*='itemInfo__main-info'] div span");
    private String executionValueXpath =
            "//span[.= '%s']/ancestor::div[contains(@class, 'grid-row--')]//div[contains(@class, '%s')]//a";
    private String defectValueXpath =
            "//span[.= '%s']/ancestor::div[contains(@class, 'grid-row--')]//span[contains(@class, '%s')]/../..//a";


    @FindBy(css = "div[class*='launchFiltersToolbar__launch']")
    private WebElementFacade launchFiltersToolbar;

    @FindBy(xpath = "//span[contains(@class, 'executionStatistics__title')]")
    private WebElementFacade executionStatistic;

    @FindBy(css = "div[class*='launchSuiteGrid__name']")
    private WebElementFacade testSuiteName;

    public boolean isLaunchesPageOpened() {
        fluentWaitUntilElementPresent(launchFiltersToolbar, 5);
        return launchFiltersToolbar.isCurrentlyVisible();
    }

    public List<String> getTableTitles() {
        ListOfWebElementFacades tableTitles = findAll(tableTitlesLocator);
        return tableTitles.stream().map(WebElementFacade::getTextContent)
                .filter(e -> !e.isEmpty()).map(String::toLowerCase).collect(Collectors.toList());
    }

    public void openTestRunByName(String testRunName) {
        findAll(testRunLinkLocator).get(getIndexOfTestRun(testRunName)).click();
    }

    private int getIndexOfTestRun(String testRunName) {
        List<String> testSuiteNames = findAll(testRunNameLocator)
                .stream().map(WebElementFacade::getTextContent).collect(Collectors.toList());
        return testSuiteNames.indexOf(testRunName);
    }

    public boolean testSuiteDataArePresent() {
        fluentWaitUntilElementPresent(testSuiteName, 5);
        return testSuiteName.isCurrentlyVisible();
    }

    public String getTestSuiteName(int suiteIndex) {
        return findAll(testSuiteNameLocator)
                .stream().map(WebElementFacade::getText).collect(Collectors.toList()).get(suiteIndex);
    }

    public String getExecutionStatisticsValue(String testSuiteName, String statisticType) {
        String locator = String.format(executionValueXpath, testSuiteName, statisticType);
        return getTextFromWebElement(find(By.xpath(locator)));
    }

    public String getDefectStatisticsValue(String testSuiteName, String defectType) {
        String locator = String.format(defectValueXpath, testSuiteName, defectType);
        return getTextFromWebElement(find(By.xpath(locator)));
    }
}
