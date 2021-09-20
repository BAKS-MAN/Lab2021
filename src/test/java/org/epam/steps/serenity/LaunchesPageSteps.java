package org.epam.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.epam.data.test_data.TestReportData;
import org.junit.Assert;
import pages.serenity.LaunchesPage;

import java.util.List;

public class LaunchesPageSteps {

    LaunchesPage launchesPage;

    @Step("check Launches page is displayed")
    public void checkLaunchesPageIsDisplayed() {
        Assert.assertTrue("Launches page is displayed", launchesPage.isLaunchesPageOpened());
    }

    @Step("check table titles on Launches page")
    public void checkTableTitles(List<String> expectedTitles) {
        List<String> actualTitles = launchesPage.getTableTitles();
        Assert.assertTrue("check table titles on Launches page", actualTitles.containsAll(expectedTitles));
    }

    @Step("open open test run")
    public void openTestRun(String testRunName) {
        launchesPage.openTestRunByName(testRunName);
        Assert.assertTrue(String.format("test suite data is displayed for '%s' test sun", testRunName),
                launchesPage.testSuiteDataArePresent());
    }

    @Step("get test suite report data")
    public TestReportData getTestSuiteReportData(String testSuiteName) {
        return TestReportData.builder()
                .testSuiteName(testSuiteName)
                .total(launchesPage.getExecutionStatisticsValue(testSuiteName, "total"))
                .passed(launchesPage.getExecutionStatisticsValue(testSuiteName, "passed"))
                .failed(launchesPage.getExecutionStatisticsValue(testSuiteName, "failed"))
                .skipped(launchesPage.getExecutionStatisticsValue(testSuiteName, "skipped"))
                .productBug(launchesPage.getDefectStatisticsValue(testSuiteName, "product_bug"))
                .autoBug(launchesPage.getDefectStatisticsValue(testSuiteName, "automation_bug"))
                .systemIssue(launchesPage.getDefectStatisticsValue(testSuiteName, "system_issue"))
                .toInvestigate(launchesPage.getDefectStatisticsValue(testSuiteName, "to_investigate"))
                .build();
    }
}
