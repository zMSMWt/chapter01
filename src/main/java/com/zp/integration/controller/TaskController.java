package com.zp.integration.controller;

import com.zp.integration.common.entity.CommonRestResult;
import com.zp.integration.mapper.UserMapper;
import com.zp.integration.pojo.dataobject.UserDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试用例
 */
@RestController
@Api("JWT 测试用例")
@RequestMapping("/test")
public class TaskController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
//    //方式一
//    public List<UserDO> list() {
//        List<UserDO> list = userMapper.findAll();
//        return list;
//    }
//    //方式二
//    public Result list(){
//        List<UserDO> list = userMapper.findAll();
//        return Result.wrapSuccessfulResult(list);
//    }
//    //方式三
    public CommonRestResult list(){
        List<UserDO> list = userMapper.findAll();
        return new CommonRestResult(list);
    }



    @GetMapping("/listTasks")
    public String listTasks() {
        return "任务列表";
    }

    @PostMapping("/newTasks")
    public String newTasks() {
        return "创建了一个新的任务";
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId") Integer id) {
        return "更新了一下 id 为：" + id + "的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId") Integer id) {
        return "删除了 id 为：" + id + "的任务";
    }





}
