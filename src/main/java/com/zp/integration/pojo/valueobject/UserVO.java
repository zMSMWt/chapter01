package com.zp.integration.pojo.valueobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO implements Serializable {

    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String phone;
    private String address;
    private String newbirthday;

}
