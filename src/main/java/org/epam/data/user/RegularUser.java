package org.epam.data.user;

import org.epam.data.dto.UserDTO;

import static org.epam.common.TestDataConstants.*;
import static org.epam.common.TestDataService.getProperty;

public class RegularUser extends UserDTO {
    public RegularUser() {
        this.setUserType(REGULAR_USER);
        this.setLogin(getProperty(USER_LOGIN));
        this.setPassword(getProperty(USER_PASSWORD));
    }
}
