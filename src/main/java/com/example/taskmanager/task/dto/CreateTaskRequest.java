package com.example.taskmanager.task.dto;

import com.example.taskmanager.task.model.PriorityType;
import com.example.taskmanager.task.model.TaskStatus;
import lombok.Data;

@Data
public class CreateTaskRequest {

    private String title;
    private String description;
    private TaskStatus taskStatus;
    private PriorityType priorityType;
}
