package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataConstants {
    public static final String REGULAR_USER = "regular user";
    public static final String ADMIN_USER = "admin";

    public static final String USER_LOGIN = "reportportal.user.login";
    public static final String USER_PASSWORD = "reportportal.user.password";

    public static final String ADMIN_LOGIN = "reportportal.admin.login";
    public static final String ADMIN_PASSWORD = "reportportal.admin.password";

    public static final String PROD_USER_LOGIN = "prod.user.login";
    public static final String PROD_USER_PASSWORD = "prod.user.password";
}
