package com.zp.chapter01.service.impl;

import com.zp.chapter01.pojo.dataobject.UserDo;
import com.zp.chapter01.mapper.UserMapper;
import com.zp.chapter01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //注入 Mapper 接口代理对象
    @Autowired
    private UserMapper userMapper;

    //注入 Redis 集群对象
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 测试：Redis 缓存
     * @return
     */
    //@Cacheable(value = "findAll")
    @Override
    public List<UserDo> findAll() {

        //测试是否使用了缓存，打印一句话到控制台
        System.out.println("如果第二次没有打印此文章，说明走了缓存，没有执行此方法！");

        List<UserDo> list = userMapper.findAll();
        return list;
    }

    public String findRedis(){
        jedisCluster.set("username", "全民出游");
        String value = jedisCluster.get("username");
        return value;
    }
}
