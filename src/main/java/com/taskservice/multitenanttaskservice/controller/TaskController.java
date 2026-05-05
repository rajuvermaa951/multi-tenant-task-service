package com.taskservice.multitenanttaskservice.controller;

import com.taskservice.multitenanttaskservice.dto.TaskDto;
import com.taskservice.multitenanttaskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/saveTask")
    public String saveTask(@RequestBody TaskDto dto)
    {
        return taskService.saveTask(dto);
    }

    @PostMapping("/assignTask")
    public String assignTask(@RequestBody TaskDto dto)
    {
        return taskService.assignTask(dto);
    }


}
