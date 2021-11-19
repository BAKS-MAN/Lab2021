package org.epam.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

import static org.epam.util.AuthApiService.getReportPortalRequestSpecification;

public class ApiClient {

    public Response getRequest(Map<String, Object> queryParams, String endpoint) {
        return RestAssured.given().queryParams(queryParams).get(endpoint);
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
}
