package org.epam.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiClient {
    private RestApiConfig restApiConfig;

    public ApiClient() {
        restApiConfig = RestApiConfig.getInstance();
    }

    public Response getRequest(Map<String, Object> queryParams, String endpoint) {
        return RestAssured.given().queryParams(queryParams).get(endpoint);
    }
}
