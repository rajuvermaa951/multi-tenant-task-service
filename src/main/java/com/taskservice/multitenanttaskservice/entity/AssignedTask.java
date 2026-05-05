package com.taskservice.multitenanttaskservice.entity;

import com.taskservice.multitenanttaskservice.entity.enums.Priority;
import com.taskservice.multitenanttaskservice.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "assigned_task" , uniqueConstraints = @UniqueConstraint(columnNames={"task_id","user_id"}))
public class AssignedTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column( name = "assigned_at")
    private Date assignedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
