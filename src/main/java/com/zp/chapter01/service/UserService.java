package com.zp.chapter01.service;

import com.zp.chapter01.pojo.dataobject.UserDo;

import java.util.List;

public interface UserService {

    /**
     * 需求：查询用户
     */
    List<UserDo> findAll();

    /**
     * 查询 Redis 集群服务
     * @return
     */
    String findRedis();
}
