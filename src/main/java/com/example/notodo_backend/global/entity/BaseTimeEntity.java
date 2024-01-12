package com.example.notodo_backend.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseTimeEntity {
    @Column(name = "created_time",
            nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime createdTime;

    @Column(name = "modified_time",
            nullable = false,
            updatable = true,
            columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime modifiedTime;
}
