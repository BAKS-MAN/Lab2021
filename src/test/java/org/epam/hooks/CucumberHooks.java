package org.epam.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.epam.util.RestApiConfig;

public class CucumberHooks {
    @Before
    public void runRestAssuredConfig() {
        RestApiConfig.getInstance();
    }

    //workaround for 'net.serenitybdd.screenplay.actors.NoStageException: No stage available - it looks like you haven't called the setTheStage() method before calling this one.'
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
