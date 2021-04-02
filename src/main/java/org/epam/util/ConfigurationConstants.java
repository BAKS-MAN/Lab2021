package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationConstants {
    public static final String ENVIRONMENT = "env";
    public static final String PROD_ENVIRONMENT = "prod";
    public static final String LOCAL_ENVIRONMENT = "local";
    public static final String BASE_URI = "base.uri";
}
