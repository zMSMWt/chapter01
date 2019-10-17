package com.zp.chapter01.dao;

import com.zp.chapter01.pojo.dataobject.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    // 根据用户名获取用户
    User findByUsername(String username);
}
