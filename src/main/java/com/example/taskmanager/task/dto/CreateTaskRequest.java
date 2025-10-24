package com.example.taskmanager.task.dto;

import com.example.taskmanager.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title is required.")
    private String title;
    private String description;

    @NotNull(message = "Task status is required.")
    private TaskStatusDto taskStatus;

    @NotNull(message = "Priority is required.")
    private PriorityTypeDto priorityType;
}
