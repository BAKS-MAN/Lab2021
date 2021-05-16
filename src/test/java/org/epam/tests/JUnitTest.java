package org.epam.tests;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.epam.runners.RunJUnitTest;
import org.epam.steps.HomePageSteps;
import org.epam.steps.LaunchesPageSteps;
import org.epam.util.FilesDataReader;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JUnitTest extends RunJUnitTest {
    private final String fileWithTableTitles = "hw5_exected_data.txt";

    @Steps
    HomePageSteps homePageSteps;

    @Steps
    LaunchesPageSteps launchesPageSteps;

    @Test
    @Title("Validate Launch Page Table titles")
    public void validateLaunchPageTable() {
        homePageSteps.loginAsRegularUser();
        homePageSteps.checkUserIsLoggedIn();
        homePageSteps.goToLaunchesPage();
        launchesPageSteps.checkLaunchesPageIsDisplayed();
        launchesPageSteps.checkTableTitles(getExpectedTableTitles());
    }

    private List<String> getExpectedTableTitles() {
        return Arrays.stream(FilesDataReader.readDataFromFile(fileWithTableTitles).split(";"))
                .map(String::trim).map(String::toLowerCase).collect(Collectors.toList());
    }
}
