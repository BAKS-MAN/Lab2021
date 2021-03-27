package org.epam.tests;

import io.restassured.RestAssured;
import org.junit.Test;

public class ApiTests {
    @Test
    public void reportPortalHealthCheck() {
        RestAssured.given().get().then().statusCode(200);
    }
}
