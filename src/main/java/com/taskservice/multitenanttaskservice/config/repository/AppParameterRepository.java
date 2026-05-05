package com.taskservice.multitenanttaskservice.config.repository;

import com.taskservice.multitenanttaskservice.config.entity.AppParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppParameterRepository extends JpaRepository<AppParameters,Long> {
    AppParameters findByParamKey(String baseUrl);
}
