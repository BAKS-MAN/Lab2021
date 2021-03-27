package org.epam.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String login;
    private String password;
    private String userType;
    private String userName;

    public UserDTO() {
    }
}
