package org.epam.tests;

import org.epam.util.ApiService;
import org.epam.util.RestApiConfig;
import org.junit.Test;

public class ApiTests {
    private static RestApiConfig restApiConfig = RestApiConfig.getInstance();
    private ApiService apiService = new ApiService();

    @Test
    public void reportPortalHealthCheck() {
        apiService.getCompositeInfo().then().statusCode(200);
    }
}
