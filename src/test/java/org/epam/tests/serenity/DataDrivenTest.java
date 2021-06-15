package org.epam.tests.serenity;

import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.assertj.core.api.SoftAssertions;
import org.epam.data.test_data.TestReportData;
import org.epam.runners.serenity.RunParameterizedJUnitTest;
import org.epam.steps.serenity.HomePageSteps;
import org.epam.steps.serenity.LaunchesPageSteps;
import org.epam.util.ExcelUtil;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//@UseTestDataFrom(value = "test_data/hw5_test_run_names.csv", separator = ';')
public class DataDrivenTest extends RunParameterizedJUnitTest {
    private final String fileWithTestRunData = "hw5_test_run_data.xlsx";

    @Steps
    HomePageSteps homePageSteps;

    @Steps
    LaunchesPageSteps launchesPageSteps;

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Demo Api Tests#1"},
                {"Demo Api Tests#2"},
                {"Demo Api Tests#3"},
                {"Demo Api Tests#4"},
                {"Demo Api Tests#5"},
                {"Demo Api Tests#6"},
                {"Demo Api Tests#7"},
                {"Demo Api Tests#8"},
                {"Demo Api Tests#9"},
                {"Demo Api Tests#10"}
        });
    }

    private String testRunName;

    public void setTestRunName(String testRunName) {
        this.testRunName = testRunName;
    }

    public DataDrivenTest(String testRunName) {
        this.testRunName = testRunName;
    }

    @Test
    public void validateTestRanData() {
        SoftAssertions softAssertions = new SoftAssertions();
        List<TestReportData> testReportDataList =
                ExcelUtil.getSheetDataFromReportExcelData(fileWithTestRunData, testRunName);
        homePageSteps.loginAsRegularUser();
        homePageSteps.checkUserIsLoggedIn();
        homePageSteps.goToLaunchesPage();
        launchesPageSteps.checkLaunchesPageIsDisplayed();
        launchesPageSteps.openTestRun(testRunName);
        for (TestReportData testReportData : testReportDataList) {
            TestReportData reportportalData =
                    launchesPageSteps.getTestSuiteReportData(testReportData.getTestSuiteName());
            softAssertions.assertThat(reportportalData)
                    .as("test suites data is not equal to expected for '%s' test run", testRunName)
                    .isEqualTo(reportportalData);
        }
        softAssertions.assertAll();
    }
}
