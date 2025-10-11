package com.example.taskmanager.task.dto;

import lombok.Data;

@Data
public class UpdateTaskRequest {

    private String title;
    private String description;
    private TaskStatusDto taskStatus;
}
