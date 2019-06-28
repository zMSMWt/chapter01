package com.zp.chapter01.service.impl;

import com.zp.chapter01.common.entity.Result;
import com.zp.chapter01.common.util.BaseUtil;
import com.zp.chapter01.common.util.BdUtil;
import com.zp.chapter01.common.util.utils;
import com.zp.chapter01.pojo.dataobject.UserDo;
import com.zp.chapter01.mapper.UserMapper;
import com.zp.chapter01.pojo.valueobject.UserVo;
import com.zp.chapter01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zp.chapter01.common.entity.StaticParam.PAGE_NUM;

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

    /**
     * 查询用户
     * @return
     */
    @Override
    public Result selectUser() {
        Map<String, Object> param = new HashMap<>();
        param.put("id", 2);
        List<UserDo> list = userMapper.selectByBaseConditionPageable(param);
        List<UserVo> res = new ArrayList<>();
        for(UserDo userDo : list){
            UserVo userVo = BdUtil.do2bo(userDo, UserVo.class);
            userVo.setNewbirthday(utils.dateToStringYMD(userDo.getBirthday()));
            res.add(userVo);
        }
        return Result.wrapSuccessfulResult(res);
    }

    /**
     * 分页查询用户列表
     * @param page
     * @return
     */
    public Result selectUserList(Integer page) {

        Map<String, Object> param = new HashMap<>();
        Integer total = userMapper.countByBaseCondition(param);
        param.put("start", (page - 1) * PAGE_NUM);
        param.put("limit", PAGE_NUM);
        List<UserDo> list = userMapper.selectByBaseConditionPageable(param);
        List<UserVo> res = new ArrayList<>();
        for(UserDo userDo : list){
            UserVo userVo = BdUtil.do2bo(userDo, UserVo.class);
            userVo.setNewbirthday(utils.dateToStringYMD(userDo.getBirthday()));
            res.add(userVo);
        }
        return Result.wrapSuccessfulResult(BaseUtil.mapRes(total, res));
    }

    public String findRedis(){
        jedisCluster.set("username", "全民出游");
        String value = jedisCluster.get("username");
        return value;
    }
}
