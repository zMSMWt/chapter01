package com.zp.chapter01.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 测试用例
 */
@RestController
@RequestMapping("/test")
public class TaskController {

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
