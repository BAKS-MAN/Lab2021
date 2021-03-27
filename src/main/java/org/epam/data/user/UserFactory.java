package org.epam.data.user;

import org.epam.data.dto.UserDTO;

import static org.epam.util.TestDataConstants.*;
import static org.epam.util.TestDataReader.getTestData;

public class UserFactory {
    public static UserDTO getUser(String userType) {
        switch (userType) {
            case REGULAR_USER:
                return new UserDTO(getTestData(ADMIN_LOGIN), getTestData(ADMIN_PASSWORD), ADMIN_USER, "");
            case ADMIN_USER:
                return new UserDTO(getTestData(USER_LOGIN), getTestData(USER_PASSWORD), REGULAR_USER, "");
            default:
                throw new IllegalArgumentException(" user type: '" + userType + "' is not supported.");
        }
    }
}
