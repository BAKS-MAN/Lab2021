package org.epam.runners.serenity;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent(threads = "4")
public class RunParameterizedJUnitTest {

    @Managed(uniqueSession = true)
    WebDriver driver;
}
