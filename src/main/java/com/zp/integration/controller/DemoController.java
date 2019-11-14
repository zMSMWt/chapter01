package com.zp.integration.controller;

import com.zp.integration.common.annotation.RequestLimit;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 限制 ip 访问次数【参照：https://blog.csdn.net/It_BeeCoder/article/details/94303699】
 * ############### 貌似不能实现，需要完善 #############################
 */
@RestController
@Api(description = "测试接口调用的限制示例")
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test")
    @RequestLimit(count=1, time = 60000)
    public String test(HttpServletRequest request) {
        return "Welcome to QMCY!";
    }
}
