package com.taskservice.multitenanttaskservice.service;

import com.taskservice.multitenanttaskservice.dto.*;
import com.taskservice.multitenanttaskservice.entity.AssignedTask;
import com.taskservice.multitenanttaskservice.entity.Task;
import com.taskservice.multitenanttaskservice.entity.enums.Priority;
import com.taskservice.multitenanttaskservice.entity.enums.TaskStatus;
import com.taskservice.multitenanttaskservice.repository.AssignedTaskRepository;
import com.taskservice.multitenanttaskservice.repository.TaskRepository;
import com.taskservice.multitenanttaskservice.serviceclient.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    CommonService commonService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserClientService userClientService;
    
    @Autowired
    AssignedTaskRepository assignedTaskRepository;

    @Override
    public String saveTask(TaskDto dto) {
        if( !commonService.isManagerOrTl(dto.getLoggedUserId(),dto.getLoggedUserType()))
        {
            return ResponseData.generateFailedRes("failed",null, AppConstant.INVALID_LOGGED_IN_USER);
        }
        if(CommonUtils.isNullOrBlank(dto.getTaskName()))
        {
            return  ResponseData.generateFailedValidationRes("Task Name is Required !");
        }

        Task task;
        if(dto.getId() == null)
        {
            try {
                Task existingTask = taskRepository.findByTaskName(dto.getTaskName()).orElseThrow();
                if(existingTask != null)
                {
                    return ResponseData.generateFailedValidationRes("Task Already Exist : "+dto.getTaskName());
                }
            }
            catch(Exception e)
            {

            }
            task = new Task();
            task.setCreatedAt(new Date());
            task.setCreatedBy(dto.getLoggedUserEmail());
            task.setStatus(TaskStatus.CREATED);
            task.setTaskId(commonService.generateTaskId());

        }
        else {
            try{
                task =  taskRepository.findById(dto.getId()).orElseThrow();
            }catch(Exception e)
            {
                return ResponseData.generateFailedRes("failed",null,"User not found with id : "+dto.getId());
            }
            task.setUpdatedAt(new Date());
            task.setUpdatedBy(dto.getLoggedUserEmail());
        }
        task.setTaskName(dto.getTaskName());
        task.setPriority(dto.getPriority() == null ? Priority.NORMAL : dto.getPriority());

        try {
            taskRepository.save(task);
            return ResponseData.generateSuccessRes("Success",null);
        }catch(Exception e)
        {
            return ResponseData.generateFailedRes("Failed",null,"Server Err");
        }


    }

    @Override
    public String assignTask(TaskDto dto) {
        if( !commonService.isManagerOrTl(dto.getLoggedUserId(),dto.getLoggedUserType()))
        {
            return ResponseData.generateFailedRes("failed",null, AppConstant.INVALID_LOGGED_IN_USER);
        }
        if(dto.getTaskId() == null)
        {
            return  ResponseData.generateFailedValidationRes("taskId is required !");
        }
        else if(CommonUtils.isNullOrBlank(dto.getUserId()))
        {
            return  ResponseData.generateFailedValidationRes("User Id is required !");
        }
        if(!taskRepository.existsByTaskId(dto.getTaskId()))
        {
            return ResponseData.generateFailedValidationRes("Invalid Task Id !");
        }
        try{
        UserDto userDto = new UserDto();
        userDto.setUserId(dto.getUserId());

        UserDto user = userClientService.getUserByUserId(userDto);
        
        if(assignedTaskRepository.existsByTaskIdAndUserId(dto.getTaskId(),dto.getUserId()))
        {
            return ResponseData.generateFailedValidationRes("Task is already assigned to user");
        }

            AssignedTask assignedTask = new AssignedTask();
            assignedTask.setAssignedAt(new Date());
            assignedTask.setAssignedTo(user.getUserId());
            assignedTask.setAssignedBy(dto.getLoggedUserEmail());
            assignedTask.setTaskId(dto.getTaskId());
            assignedTask.setUserId(dto.getUserId());
            assignedTask.setStatus(TaskStatus.ASSIGNED);
            
            assignedTaskRepository.save(assignedTask);


        return ResponseData.generateSuccessRes("Success",user);
        }
        catch (Exception e)
        {
            return ResponseData.generateFailedRes("failed",null,"Error while fetching User Details");
        }

    }
}
