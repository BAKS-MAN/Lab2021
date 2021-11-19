package org.epam.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;

import static com.google.api.client.http.HttpStatusCodes.STATUS_CODE_OK;
import static org.epam.util.ConfigDataReader.getConfigData;
import static org.epam.util.ConfigurationConstants.API_AUTHORIZATION_HEADER_VALUE;
import static org.epam.util.ConfigurationConstants.BASE_URI;
import static org.epam.util.TestDataConstants.ADMIN_LOGIN;
import static org.epam.util.TestDataConstants.ADMIN_PASSWORD;
import static org.epam.util.TestDataReader.getTestData;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthApiService {
    private static final String UAT = "uat";
    private static final String SSO_OAUTH_TOKEN = "/sso/oauth/token";
    private static final ThreadLocal<String> ACCESS_TOKEN = new ThreadLocal<>();
    private static final ThreadLocal<RequestSpecification> AUTH_SPECIFICATION = new ThreadLocal<>();


    public static void clearAccessToken() {
        ACCESS_TOKEN.remove();
    }

    public static void clearAuthSpecification() {
        AUTH_SPECIFICATION.remove();
    }

    public static String getAccessToken() {
        if (StringUtils.isEmpty(ACCESS_TOKEN.get())) {
            Response response = RestAssured.given()
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .header(HttpHeaders.AUTHORIZATION, getConfigData(API_AUTHORIZATION_HEADER_VALUE))
                    .formParam("grant_type", "password")
                    .formParam("username", getTestData(ADMIN_LOGIN))
                    .formParam("password", getTestData(ADMIN_PASSWORD))
                    .post(getConfigData(BASE_URI) + UAT + SSO_OAUTH_TOKEN);
            ACCESS_TOKEN.set(response.then().statusCode(STATUS_CODE_OK).extract().path("access_token"));
        }
        return ACCESS_TOKEN.get();
    }

    public static RequestSpecification getReportPortalRequestSpecification() {
        if (AUTH_SPECIFICATION.get() == null) {
            AUTH_SPECIFICATION.set(getAuthSpecification());
        }
        return AUTH_SPECIFICATION.get();
    }

    private static RequestSpecification getAuthSpecification() {
        RestAssured.defaultParser = Parser.JSON;
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .setContentType(ContentType.JSON)
                .build();
    }
}
