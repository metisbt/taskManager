package com.example.taskmanager.task.dto;

import lombok.Data;

@Data
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private PriorityTypeDto priorityType;
    private TaskStatusDto taskStatus;
}
