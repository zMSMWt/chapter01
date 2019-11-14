package com.zp.integration.controller;

import com.zp.integration.dao.UserRepository;
import com.zp.integration.pojo.dataobject.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 注册(登录)
 * 现在认证的路径统一了一下也是挺好
 * 注册：/auth/register
 * 登录：/auth/login
 */
@RestController
@RequestMapping("/auth")
@Api(description = "注册（登录）")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> registerUser) {
        User user = new User();
        user.setUsername(registerUser.get("username"));
        // 记得注册的时候把密码加密一下
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        User saveUser = userRepository.save(user);
        return saveUser.toString();
    }

    /**
     * UsernamePasswordAuthenticationFilter 源代码
     * public UsernamePasswordAuthenticationFilter() {
     * 		super(new AntPathRequestMatcher("/login", "POST"));
     * }
     * 可以看出来默认是/login，所以登录直接使用这个路径就可以啦~当然也可以自定义。
     */
}
