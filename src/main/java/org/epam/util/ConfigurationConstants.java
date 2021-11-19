package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationConstants {
    public static final String ENVIRONMENT = "env";
    public static final String PROD_ENVIRONMENT = "prod";
    public static final String LOCAL_ENVIRONMENT = "local";
    public static final String LOCAL_DOCKER_ENVIRONMENT = "docker";
    public static final String BASE_URI = "base.uri";
    public static final String BASE_API_URI = "base.api.uri";
    public static final String API_AUTHORIZATION_HEADER_VALUE = "api.authorization.header.value";
    public static final String LOCAL_FOLDER_TO_STORE_FILES = "src/test/resources/files/";
}
