package com.taskservice.multitenanttaskservice.entity;

import com.taskservice.multitenanttaskservice.entity.enums.Priority;
import com.taskservice.multitenanttaskservice.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "task",uniqueConstraints = @UniqueConstraint(columnNames = {"task_name","tast_id"}))
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="task_name")
    private String taskName;

    @Column(name = "tast_id")
    private String taskId;

    @Column(name="status")
    @Enumerated(EnumType.STRING)                             //0 -> Inactive , 1 -> completed, 2 ->created ,3 -> assigned , 4 -> InProgress
    private TaskStatus status;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "deleted")
    private String deleted;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;
}
