package com.zp.integration.service;

import com.zp.integration.common.entity.Result;
import com.zp.integration.pojo.dataobject.UserDO;

import java.util.List;

public interface UserService {

    /**
     * 需求：查询用户
     */
    List<UserDO> findAll();

    /**
     * 查询用户
     * @return
     */
    Result selectUser();

    /**
     * 分页查询用户列表
     * @param page
     * @return
     */
    Result selectUserList(Integer page);

    /**
     * 查询 Redis 集群服务
     * @return
     */
    String findRedis();
}
