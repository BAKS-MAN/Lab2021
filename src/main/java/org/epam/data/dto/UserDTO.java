package org.epam.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class UserDTO implements Serializable {
    private String login;
    private String password;
    private String userType;
    private String userName;

    public UserDTO() {
    }
}
