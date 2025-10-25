package com.example.taskmanager.task.dto;

import com.example.taskmanager.user.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private PriorityTypeDto priorityType;
    private TaskStatusDto taskStatus;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
    private String deletedBy;
    private LocalDateTime deletedAt;
}
