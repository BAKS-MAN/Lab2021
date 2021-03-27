package org.epam.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiService {

    private static final String COMPOSITE_INFO = "/composite/info";
    private ApiClient apiClient = new ApiClient();

    public Response getCompositeInfo() {
        String url = RestAssured.baseURI + COMPOSITE_INFO;
        Map<String, Object> queryParams = new HashMap<>();
        return apiClient.getRequest(queryParams, url);
    }
}
