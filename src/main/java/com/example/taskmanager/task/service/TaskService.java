package com.example.taskmanager.task.service;

import com.example.taskmanager.task.dto.CreateTaskRequest;
import com.example.taskmanager.task.dto.TaskResponse;
import com.example.taskmanager.task.dto.UpdateTaskRequest;

import java.util.List;


public interface TaskService {
    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    TaskResponse createTask (CreateTaskRequest createTaskRequest);

    TaskResponse updateTask (Long id, UpdateTaskRequest updateTask);

    void deleteTaskById(Long id);

    TaskResponse getTaskByTitle(String title);
}
