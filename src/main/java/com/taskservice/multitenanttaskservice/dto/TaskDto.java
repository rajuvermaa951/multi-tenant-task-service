package com.taskservice.multitenanttaskservice.dto;

import com.taskservice.multitenanttaskservice.entity.enums.Priority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String taskId;
    private String userId;
    private String taskName;
    private String loggedUserId;
    private String loggedUserEmail;
    private String managerId;
    private String status;
    private Priority priority;
    private String organizationId;
    private String loggedUserType;

    public void trim()
    {
        taskName = taskName == null ? null : taskName.trim();
        loggedUserEmail = loggedUserEmail == null ? null : loggedUserEmail.trim();
        loggedUserId = loggedUserId == null ? null : loggedUserId.trim();
        managerId = managerId == null ? null : managerId.trim();
        status = status == null ? null : status.trim();
        organizationId = organizationId == null ? null : organizationId.trim();
        loggedUserType = loggedUserType == null ? null : loggedUserType.trim();
    }

}
