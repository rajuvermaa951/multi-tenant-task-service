package com.taskservice.multitenanttaskservice.repository;

import com.taskservice.multitenanttaskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findByTaskName(String taskName);

    boolean existsByTaskId(String taskId);

}
