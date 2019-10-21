package com.zp.integration.common.entity;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private Integer rememberMe;
}
