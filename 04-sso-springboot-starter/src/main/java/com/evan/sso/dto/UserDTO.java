package com.evan.sso.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserDTO implements Serializable{
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String realName;
}
