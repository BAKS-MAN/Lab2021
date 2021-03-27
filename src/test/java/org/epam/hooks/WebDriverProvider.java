package org.epam.hooks;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        WebDriverProvider.driver = driver;
    }
}
