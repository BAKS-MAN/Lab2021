package org.epam.steps.selenium;

import com.epam.reportportal.annotations.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import pages.selenium.DashboardPage;

import static org.epam.util.DashboardScreenSize.*;

public class DashboardPageSteps {

    DashboardPage dashboardPage;

    public DashboardPageSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
    }

    @Step("dashboard page is open")
    public void dashboardPageIsOpen() {
        Assertions.assertTrue(dashboardPage.isDashboardsPageDisplayed(), "Dashboard page is displayed");
    }

    @Step("open 'Demo' dashboard")
    public void openDemoDashboard() {
        Assertions.assertTrue(dashboardPage.isSpecifiedByNameDashboardDisplayed("Demo"),
                "Demo dashboard is not found");
        dashboardPage.openSpecifiedByNameDashboard("Demo");
        Assertions.assertTrue(dashboardPage.isDashBoardOpened("Demo"),
                "Demo dashboard is not opened");
    }

    @Step("demo widgets are displayed")
    public void demoWidgetsAreDisplayed() {
        int widgetQuantity = dashboardPage.getWidgetQuantity();
        Assertions.assertTrue(widgetQuantity >= 2,
                String.format("Not enough demo widget for test, expected >=2, but was %s", widgetQuantity));
    }

    @Step("check first widget content after resize to half screen width")
    public void checkFirstWidgetContentAfterResizeToHalfScreenWidth() {
        int firstWidgetIndex = 0;
        int widgetsContainerHalfWidth = (int) (dashboardPage.getWidgetsContainerWidth() * HALF_SCREEN_WIDTH.getValue());
        int widgetContentWidthBeforeResize = dashboardPage.getWidgetStatisticsChartWidth(firstWidgetIndex);
        int widgetWidthAfterResize = dashboardPage.resizeWidgetWidth(firstWidgetIndex, HALF_SCREEN_WIDTH.getValue());
        int widgetContentWidthAfterResize = dashboardPage.getWidgetStatisticsChartWidth(firstWidgetIndex);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(widgetWidthAfterResize)
                .as("Widget width is not equal to half screen(widget container) size.")
                .isEqualTo(widgetsContainerHalfWidth);
        softAssertions.assertThat(widgetContentWidthAfterResize)
                .as("Widget content(statistics chart) width is not changed after widget resize.")
                .isNotEqualTo(widgetContentWidthBeforeResize);
        softAssertions.assertAll();
    }

    @Step("second widget is moved when first widget is resized to full screen width")
    public void checkSecondWidgetLocationAfterFirstWidgetResize() {
        Point secondWidgetLocationBeforeResize = dashboardPage.getWidgetLocation(1);
        dashboardPage.resizeWidgetWidth(0, FULL_SCREEN_WIDTH.getValue());
        Point secondWidgetLocationAfterResize = dashboardPage.getWidgetLocation(1);
        Assertions.assertNotEquals(secondWidgetLocationBeforeResize, secondWidgetLocationAfterResize,
                "second widget location was not changed when first widget was resized to full screen width");
    }


    @Step("resize first widget to minimal screen width")
    public void resizeFirstWidgetToMinimalScreenWidth() {
        int firstWidgetIndex = 0;
        int widgetWidthBeforeResize = dashboardPage.getWidgetWidth(firstWidgetIndex);
        int widgetWidthAfterResize = dashboardPage.resizeWidgetWidth(firstWidgetIndex, MINIMAL_SCREEN_WIDTH.getValue());
        Assertions.assertNotEquals(widgetWidthBeforeResize, widgetWidthAfterResize,
                "First widget size was not changed after resize");
    }

    @Step("resize last widget wider than dashboard itself")
    public void resizeLastWidgetWiderThenDashboard() {
        int lestWidgetIndex = dashboardPage.getWidgetQuantity() - 1;
        int widgetsContainerWidth = dashboardPage.getWidgetsContainerWidth();
        int widgetWidthAfterResize = dashboardPage.resizeWidgetWiderThanWidgetContainer(lestWidgetIndex);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(widgetWidthAfterResize)
                .as("Last widget width is wider than widget container itself")
                .isLessThanOrEqualTo(widgetsContainerWidth);
        softAssertions.assertAll();
    }
}
