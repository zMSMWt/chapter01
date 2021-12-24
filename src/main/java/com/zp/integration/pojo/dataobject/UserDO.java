package com.zp.integration.pojo.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDO implements Serializable {

    private Long id;
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

    public UserDO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
