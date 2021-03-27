package org.epam.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.epam.util.ConfigurationConstants.BASE_URI;
import static org.epam.util.PropertyReader.getConfigData;

public class RestApiConfig {
    private static RestApiConfig instance;

    public static RestApiConfig getInstance() {
        if (instance == null) {
            instance = new RestApiConfig();
        }
        return instance;
    }

    private RestApiConfig() {
        RestAssured.requestSpecification = requestSpecificationBuilder();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON));
    }

    private RequestSpecification requestSpecificationBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(getConfigData(BASE_URI))
                .setContentType(ContentType.JSON)
                .build();
    }
}
