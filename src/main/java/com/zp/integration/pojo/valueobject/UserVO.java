package com.zp.integration.pojo.valueobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zp.integration.pojo.dataobject.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String sex;
    private String phone;
    private String address;
    private String newbirthday;

}
