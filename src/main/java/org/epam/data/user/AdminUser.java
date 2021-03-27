package org.epam.data.user;

import org.epam.data.dto.UserDTO;

import static org.epam.common.TestDataConstants.*;
import static org.epam.common.TestDataService.getProperty;

public class AdminUser extends UserDTO {
    public AdminUser() {
        this.setUserType(ADMIN_USER);
        this.setLogin(getProperty(ADMIN_LOGIN));
        this.setPassword(getProperty(ADMIN_PASSWORD));
    }
}
