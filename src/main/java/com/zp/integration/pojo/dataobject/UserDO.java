package com.zp.integration.pojo.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Date birthday;

    private Integer age;

    private String sex;

    private String phone;

    private String address;

    private String role;

    public UserDO(){
        super();
    }

    public UserDO(String username) {
        this.username = username;
    }

    public UserDO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
