package org.epam.data.user;

import org.epam.data.dto.UserDTO;

import static org.epam.common.TestDataConstants.ADMIN_USER;
import static org.epam.common.TestDataConstants.REGULAR_USER;

public class UserFactory {
    public static UserDTO getUser(String userType) {
        UserDTO user;
        switch (userType) {
            case REGULAR_USER:
                user = new AdminUser();
                break;
            case ADMIN_USER:
                user = new RegularUser();
                break;
            default:
                throw new IllegalArgumentException(" user type: '" + userType + "' is not supported.");
        }
        return user;
    }
}
