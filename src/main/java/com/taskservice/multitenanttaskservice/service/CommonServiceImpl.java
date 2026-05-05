package com.taskservice.multitenanttaskservice.service;

import com.taskservice.multitenanttaskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService{

    private final TaskRepository taskRepository;

    public CommonServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean isManagerOrTl(String loggedUserId, String loggedUserType) {
        return loggedUserId != null && !loggedUserId.isBlank() && ( loggedUserType.equals("TL") || loggedUserType.equals("MN"));
    }

    @Override
    public String generateTaskId() {
        String taskId;

        do {
            taskId = "TASK" + System.currentTimeMillis()
                    + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        } while (taskRepository.existsByTaskId(taskId)); // DB check

        return taskId;
    }
    }

