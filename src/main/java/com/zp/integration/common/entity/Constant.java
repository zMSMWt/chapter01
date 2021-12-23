package com.zp.integration.common.entity;

import lombok.Getter;

/**
 * @ClassName Constant
 * @Description 常量类
 * @Author zp
 * @Date 2021/12/23 6:45
 * @Version 1.0
 */

public class Constant {

    public final static String TOKEN_NAME = "msmw_";

    public enum Redis {
        TOKEN_PREFIX(TOKEN_NAME);

        @Getter
        private String name;

        Redis(String name){
            this.name = name;
        }
    }
}
