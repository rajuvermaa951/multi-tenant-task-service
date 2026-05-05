package com.taskservice.multitenanttaskservice.repository;

import com.taskservice.multitenanttaskservice.entity.AssignedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedTaskRepository extends JpaRepository<AssignedTask,Long> {
    boolean existsByTaskIdAndUserId(String taskId, String userId);

}
