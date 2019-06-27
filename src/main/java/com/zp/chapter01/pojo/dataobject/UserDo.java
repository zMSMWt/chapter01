package com.zp.chapter01.pojo.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDo implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Date birthday;
    private Integer age;
    private String sex;
    private String phone;
    private String address;

    public UserDo(){
        super();
    }

    public UserDo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDo(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
