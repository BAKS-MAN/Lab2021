package org.epam.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

import static org.epam.util.AuthApiService.getReportPortalRequestSpecification;
import static org.epam.util.ConfigDataReader.getConfigData;
import static org.epam.util.ConfigurationConstants.BASE_URI;
import static org.epam.util.ConfigurationConstants.SLACK_URI;

public class ApiClient {

    public Response getUiEndpointRequestWithParams(Map<String, Object> queryParams, String endpoint) {
        return RestAssured.given().baseUri(getConfigData(BASE_URI)).queryParams(queryParams).get(endpoint);
    }

    public Response getAuthorizedRequest(String endpoint) {
        return RestAssured.given(getReportPortalRequestSpecification()).get(endpoint);
    }

    public Response postAuthorizedRequest(String endpoint, Object body) {
        return RestAssured.given(getReportPortalRequestSpecification()).body(body).post(endpoint);
    }

    public Response putAuthorizedRequest(String endpoint, Object body) {
        return RestAssured.given(getReportPortalRequestSpecification()).body(body).put(endpoint);
    }

    public Response deleteAuthorizedRequest(String endpoint) {
        return RestAssured.given(getReportPortalRequestSpecification()).delete(endpoint);
    }

    public Response postSlackRequest(String body) {
        return RestAssured.given().baseUri(SLACK_URI).body(body).post();
    }
}
