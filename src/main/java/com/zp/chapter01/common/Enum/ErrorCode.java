package com.zp.chapter01.common.Enum;


import lombok.Getter;

public enum ErrorCode {

    MANAGEUSER_ERROR(999999,"用户名或者密码错误！"),
    SAVE_ERROR(999999,"保存失败！"),
    DEL_ERROR(999999,"删除失败！"),
    PHONE_ERROR(999999,"电话错误！"),
    OLDPWD_ERROR(999999,"旧密码输入错误！"),
    RENEWPWD_ERROR(999999,"两次输入的新密码不一致！"),
    ADDMANAGEUSER_ERROR(999999,"该用户名已存在！"),
    MANAGE_USER_NO_LOGIN(999999,"请先登录！"),
    UPLOAD_FILE_ERROR(999999,"上传图片失败！"),
    UPLOAD_FILE_ISBIG(999999,"上传图片不能超过2MB！"),
    TOKEN_EXPIRED(999999,"Token 已过期,请重新登录！");

    @Getter
    private Integer id;
    @Getter
    private String name;

    ErrorCode(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
