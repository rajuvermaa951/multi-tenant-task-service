package com.taskservice.multitenanttaskservice.service;

import com.taskservice.multitenanttaskservice.dto.TaskDto;

public interface TaskService {
    String saveTask(TaskDto dto);

    String assignTask(TaskDto dto);
}
