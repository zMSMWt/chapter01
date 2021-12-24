package com.zp.integration.dao;

import com.zp.integration.pojo.dataobject.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    // 根据用户名获取用户
    User findByUsername(String username);
}
