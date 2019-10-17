package com.zp.chapter01.service.impl;

import com.zp.chapter01.common.entity.JwtUser;
import com.zp.chapter01.dao.UserRepository;
import com.zp.chapter01.pojo.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 使用 springSecurity 需要实现 UserDetailsService 接口供权限框架调用，
 * 该方法只需要实现一个方法就可以了，那就是根据用户名去获取用户，即
 * UserRepository 定义的方法，此处直接调用。
 * <p>
 * 由于接口方法需要返回一个 UserDetails 类型的接口，
 * 需得再写一个类实现一下此接口(UserDetails)。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        return new JwtUser(user);
    }
}
