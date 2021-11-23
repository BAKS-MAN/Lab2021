package org.epam.util;

import io.restassured.response.Response;
import org.epam.api.requests.DashboardAddWidgetData;
import org.epam.api.requests.DashboardAddWidgetRequest;
import org.epam.api.responses.FilterData;
import org.epam.data.dto.api.DashboardWidgetDTO;
import java.util.HashMap;
import java.util.Map;

import static org.epam.api.requests.DashboardAddWidgetData.getDashboardAddWidgetData;
import static org.epam.api.requests.DashboardRequest.getDashboardRequestObject;
import static org.epam.api.requests.WidgetCreateRequest.getCreateWidgetRequestObject;

public class ApiService {

    private static final String COMPOSITE_INFO = "/composite/info";
    private static final String PROJECT_NAMES = "/v1/project/names";
    private static final String WIDGET_TEMPLATE = "/v1/%s/widget";
    private static final String SPECIFIED_WIDGET_TEMPLATE = "/v1/%s/widget/%s";
    private static final String FILTER_TEMPLATE = "/v1/%s/filter";
    private static final String SPECIFIED_DASHBOARD_WIDGET_TEMPLATE = "/v1/%s/dashboard/%s/%s";
    private static final String DASHBOARD_TEMPLATE = "/v1/%s/dashboard";
    private static final String SPECIFIED_DASHBOARD_TEMPLATE = "/v1/%s/dashboard/%s";
    private static final String SPECIFIED_DASHBOARD_ADD_WIDGET_TEMPLATE = "/v1/%s/dashboard/%s/add";
    private ApiClient apiClient = new ApiClient();

    public Response getCompositeInfo() {
        Map<String, Object> queryParams = new HashMap<>();
        return apiClient.getUiEndpointRequestWithParams(queryParams, COMPOSITE_INFO);
    }

    public Response getAllProjectNames() {
        return apiClient.getAuthorizedRequest(PROJECT_NAMES);
    }

    public Response getFiltersForProject(String projectName) {
        String uri = String.format(FILTER_TEMPLATE, projectName);
        return apiClient.getAuthorizedRequest(uri);
    }

    public Response createWidget(String projectName, FilterData filterData, DashboardWidgetDTO widgetToAdd) {
        String uri = String.format(WIDGET_TEMPLATE, projectName);
        return apiClient.postAuthorizedRequest(uri, getCreateWidgetRequestObject(filterData, widgetToAdd));
    }

    public Response updateWidget(String projectName, FilterData filterData, DashboardWidgetDTO widgetToAdd) {
        String uri = String.format(SPECIFIED_WIDGET_TEMPLATE, projectName, widgetToAdd.getWidgetId());
        return apiClient.putAuthorizedRequest(uri, getCreateWidgetRequestObject(filterData, widgetToAdd));
    }

    public Response deleteWidgetFromDashboard(String projectName, String dashboardId, String widgetId) {
        String uri = String.format(SPECIFIED_DASHBOARD_WIDGET_TEMPLATE, projectName, dashboardId, widgetId);
        return apiClient.deleteAuthorizedRequest(uri);
    }

    public Response createDashboard(String projectName) {
        String uri = String.format(DASHBOARD_TEMPLATE, projectName);
        return apiClient.postAuthorizedRequest(uri, getDashboardRequestObject());
    }

    public Response addWidgetToDashboard(String projectName, String dashboardId, DashboardWidgetDTO widgetToAdd) {
        String uri = String.format(SPECIFIED_DASHBOARD_ADD_WIDGET_TEMPLATE, projectName, dashboardId);
        DashboardAddWidgetData dashboardAddWidgetData = getDashboardAddWidgetData(widgetToAdd);
        return apiClient.putAuthorizedRequest(uri, new DashboardAddWidgetRequest(dashboardAddWidgetData));
    }

    public Response deleteDashboard(String projectName, String dashboardId) {
        String uri = String.format(SPECIFIED_DASHBOARD_TEMPLATE, projectName, dashboardId);
        return apiClient.deleteAuthorizedRequest(uri);
    }

    public Response postSlackNotification(String notificationMessage) {
        return apiClient.postSlackRequest(notificationMessage);
    }
}
