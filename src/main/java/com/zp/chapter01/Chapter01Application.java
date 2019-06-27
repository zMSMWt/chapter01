package com.zp.chapter01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin/")
//@EnableCaching  // 开启缓存注解
@SpringBootApplication
public class Chapter01Application {

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("home")
    String home(HttpServletRequest request) {
        String userName = "zp";
        int count = 100;
        count++;
        return "欢迎您 " + userName + "，这是您的第 " + count + " 次登录！";
    }

    public static void main(String[] args) {

        SpringApplication.run(Chapter01Application.class, args);
    }

}
