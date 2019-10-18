package com.zp.chapter01.controller;

import com.zp.chapter01.common.entity.Result;
import com.zp.chapter01.pojo.dataobject.UserDO;
import com.zp.chapter01.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Api("userTest")
public class UserController {

    //注入 Service 服务对象
    @Autowired
    private UserService userService;

    /**
     * 需求：返回基本类型 json 格式数据
     */
    @RequestMapping("hello")
    public String showHello(){
        return "hello QMCY";
    }

    /**
     * 需求：返回 POJO 对象
     */
    @RequestMapping("pojo")
    public UserDO showUser(){

        //创建 UserDO 对象
        UserDO user = new UserDO();
        user.setId(1);
        user.setUsername("qm");
        user.setPassword("123");
        user.setBirthday(new Date());
        user.setAge(25);
        user.setSex("0");
        user.setPhone("13212345678");
        user.setAddress("杭州西湖");
        return user;
    }

    /**
     * 需求：返回 Map 集合对象
     */
    @RequestMapping("maps")
    public Map<String, Object> showMaps(){
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("username", "qmcy");
        maps.put("password","123456");
        return maps;
    }

    /**
     * 需求：返回 List 集合对象
     */
    @RequestMapping("list")
    public List<UserDO> showList(){
        List<UserDO> userList = new ArrayList<UserDO>();
        UserDO user1 = new UserDO();
        user1.setId(1);
        user1.setUsername("zp");
        user1.setPassword("z123");

        UserDO user2 = new UserDO();
        user2.setId(2);
        user2.setUsername("gzq");
        user2.setPassword("g123");

        userList.add(user1);
        userList.add(user2);

        return userList;
    }

    /**
     * 需求：整合 SSM
     */
    @RequestMapping("ssm")
    public List<UserDO> findAll(){
        List<UserDO> list = userService.findAll();
        return list;
    }

    /**
     * 查询用户
     * @return
     */
    @RequestMapping("selectuser")
    public Result selectUser(){
        return userService.selectUser();
    }

    /**
     * 查询用户
     * @return
     */
    @RequestMapping("selectuserlist")
    public Result selectUserList(@RequestParam("page") Integer page){
        return userService.selectUserList(page);
    }

    /**
     * 需求：查询 Redis 集群服务
     */
    @RequestMapping("redis")
    public String findRedis(){
       String findRedis = userService.findRedis();
        return findRedis;
    }
}
