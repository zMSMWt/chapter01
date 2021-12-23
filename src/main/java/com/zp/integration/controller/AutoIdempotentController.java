package com.zp.integration.controller;

import com.zp.integration.common.annotation.AutoIdempotent;
import com.zp.integration.common.entity.Result;
import com.zp.integration.common.token.TokenService;
import com.zp.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AutoIdempotentController
 * @Description
 * @Author zp
 * @Date 2021/12/23 8:57
 * @Version 1.0
 */

@RestController
public class AutoIdempotentController {

    @Resource
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/get/token")
    public Result getToken() throws Exception{
        String token = tokenService.createToken();
        return Result.wrapSuccessfulResult(token);
    }

    @AutoIdempotent
    @PostMapping("/test/idempotence")
    public Result testIdempotence() {
        return userService.selectUser();
    }
}
