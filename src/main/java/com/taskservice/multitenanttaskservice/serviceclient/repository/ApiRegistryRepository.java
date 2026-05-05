package com.taskservice.multitenanttaskservice.serviceclient.repository;

import com.taskservice.multitenanttaskservice.serviceclient.entity.ApiRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRegistryRepository extends JpaRepository<ApiRegistry,Long> {
    List<ApiRegistry> findByIsActive(String isActive);
}
