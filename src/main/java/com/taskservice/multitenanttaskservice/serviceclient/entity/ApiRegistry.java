package com.taskservice.multitenanttaskservice.serviceclient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "api_registry",uniqueConstraints =
@UniqueConstraint(columnNames = {"service_name","service_code"}))

public class ApiRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="service_name")
    private String serviceName;

    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "method_type")
    private String methodType;

    @Column(name = "api_path")
    private String apiPath;

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "created_at")
    private String createdAt;

}
