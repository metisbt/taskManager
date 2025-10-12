package com.example.taskmanager.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private TaskStatusDto taskStatus;
}
