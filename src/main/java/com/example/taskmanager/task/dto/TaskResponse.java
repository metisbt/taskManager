package com.example.taskmanager.task.dto;

import com.example.taskmanager.task.model.PriorityType;
import com.example.taskmanager.task.model.TaskStatus;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private PriorityTypeDto priorityType;
    private TaskStatusDto taskStatus;
}
