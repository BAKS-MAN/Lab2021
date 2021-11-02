package org.epam.tests.api;

import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.epam.api.responses.FilterData;
import org.epam.api.responses.FilterResponse;
import org.epam.data.dto.api.DashboardWidgetDTO;
import org.epam.util.ApiService;
import org.epam.util.RestApiConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static com.google.api.client.http.HttpStatusCodes.STATUS_CODE_CREATED;
import static com.google.api.client.http.HttpStatusCodes.STATUS_CODE_OK;
import static org.epam.data.dto.api.DashboardWidgetDTO.getDashboardWidgetObject;
import static org.epam.util.AuthApiService.clearAccessToken;
import static org.epam.util.AuthApiService.clearAuthSpecification;


class ApiTests {
    ApiService apiService = new ApiService();
    private static String projectName;
    private static DashboardWidgetDTO widget;

    @BeforeAll
    static void initApiClient() {
        RestApiConfig.getInstance();
    }

    @AfterAll
    static void clearSession() {
        clearAccessToken();
        clearAuthSpecification();
    }

    @Test
    void widgetTestViaApi() {
        createWidgetViaApi();
        //Update widget
        widget.setDescription("Updated widget");
        Response updateWidgetResponse = apiService.updateWidget(projectName, getFilterDataForCurrentProject(), widget);
        checkResponseCodeIsSuccessful(updateWidgetResponse);
    }

    private void createWidgetViaApi() {
        if (StringUtils.isEmpty(widget.getWidgetId())) {
            widget = getDashboardWidgetObject();
            Response createWidgetResponse =
                    apiService.createWidget(projectName, getFilterDataForCurrentProject(), widget);
            checkResponseCodeIsSuccessful(createWidgetResponse);
            String widgetId = createWidgetResponse.jsonPath().getMap("$").get("id").toString();
            widget.setWidgetId(widgetId);
        }
    }

    @Test
    void dashboardTestViaApi() {
        String projectName = getProjectName();
        //Create dashboard
        Response createDashboardResponse = apiService.createDashboard(projectName);
        checkResponseCodeIsSuccessful(createDashboardResponse);
        String dashboardId = createDashboardResponse.jsonPath().getMap("$").get("id").toString();
        //Modify dashboard by adding widget
        createWidgetViaApi();
        Assumptions.assumeTrue(StringUtils.isNotEmpty(widget.getWidgetId()),
                "Looks like widget was not created. Test execution can't be continued");
        Response addWidgetToDashboardResponse = apiService.addWidgetToDashboard(projectName, dashboardId, widget);
        checkResponseCodeIsSuccessful(addWidgetToDashboardResponse);
        //Remove widget from dashboard
        Response deleteWidgetResponse =
                apiService.deleteWidgetFromDashboard(projectName, dashboardId, widget.getWidgetId());
        checkResponseCodeIsSuccessful(deleteWidgetResponse);
        //Remove dashboard
        Response deleteDashboardResponse = apiService.deleteDashboard(projectName, dashboardId);
        checkResponseCodeIsSuccessful(deleteDashboardResponse);
    }

    private String getProjectName() {
        if (StringUtils.isEmpty(projectName)) {
            Response projectNamesResponse = apiService.getAllProjectNames();
            checkResponseCodeIsSuccessful(projectNamesResponse);
            projectName = projectNamesResponse.jsonPath().getList("$").stream().map(Object::toString)
                    .findFirst().orElseThrow(() -> new NoSuchElementException("There are no project name in response"));
        }
        return projectName;
    }

    private FilterData getFilterDataForCurrentProject() {
        Response filtersResponse = apiService.getFiltersForProject(getProjectName());
        checkResponseCodeIsSuccessful(filtersResponse);
        FilterResponse filterResponse = filtersResponse.as(FilterResponse.class);
        Assertions.assertThat(filterResponse.getContent())
                .as("There are no filters in response for current project.")
                .isNotEmpty();
        return filterResponse.getContent().get(0);
    }

    private void checkResponseCodeIsSuccessful(Response response) {
        Assertions.assertThat(response.getStatusCode())
                .as("Incorrect response status was received")
                .isBetween(STATUS_CODE_OK, STATUS_CODE_CREATED);
    }
}
