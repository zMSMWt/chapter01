package com.zp.integration.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zp.integration.common.entity.LoginUser;
import com.zp.integration.common.util.JwtTokenUtils;
import com.zp.integration.common.entity.JwtUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * JWTAuthenticationFilter 继承于 UsernamePasswordAuthenticationFilter
 * 该拦截器用于获取用户登录的信息，
 * 只需创建一个 token 并调用 authenticationManager.authenticate() 让 spring-security 去进行验证就可以了，
 * 不用自己查数据库在对比密码，
 * 这一步交给 spring 去操作。
 * 此操作类似 shiro 的 subject.login(new UsernamePasswordToken())，验证的事交给框架。
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成 token 并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用 getPrincipal() 方法会返回一个实现了 UserDetails 接口的对象
        // 所以就是 JwtUser 啦
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
//        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
        /****************** 改造起 **********************/
        boolean isRemember = rememberMe.get() == 1;
        String role = "";
        // 因为在 JwtUser 中存在了权限信息，可以直接获取，由于只有一个角色就这么处理了
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        // 根据用户名，角色创建 token
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
        /****************** 改造止 **********************/
        // 返回创建成功的 token
        // 但是这里创建的 token 只是单纯的 token
        // 按照 jwt 的规定，最后请求的格式应该是  Bearer token
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}

