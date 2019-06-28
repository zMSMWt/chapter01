package com.zp.chapter01.common.Enum;


import lombok.Getter;

public enum ErrorCode {

    MANAGEUSER_ERROR(1001,"用户名或者密码错误！"),
    SAVE_ERROR(1002,"保存失败！"),
    DEL_ERROR(1003,"删除失败！"),
    PHONE_ERROR(1004,"电话错误！"),
    OLDPWD_ERROR(1005,"旧密码输入错误！"),
    RENEWPWD_ERROR(1006,"两次输入的新密码不一致！"),
    ADDMANAGEUSER_ERROR(1007,"该用户名已存在！"),
    MANAGE_USER_NO_LOGIN(1008,"请先登录！"),
    UPLOAD_FILE_ERROR(1009,"上传图片失败！"),
    UPLOAD_FILE_ISBIG(1010,"上传图片不能超过2MB！");

    @Getter
    private Integer id;
    @Getter
    private String name;

    ErrorCode(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
