package org.epam.stepdefs;

import io.cucumber.java.en.When;
import org.epam.util.ApiService;

import static java.net.HttpURLConnection.HTTP_OK;

public class ApiStepDefs {
    private ApiService apiService = new ApiService();

    @When("^local report portal is up and running$")
    public void localReportPortalHealthCheck() {
        apiService.getCompositeInfo().then().statusCode(HTTP_OK);
    }
}
