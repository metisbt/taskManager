package com.example.taskmanager.task.controller;

import com.example.taskmanager.task.dto.ApiResponse;
import com.example.taskmanager.task.dto.CreateTaskRequest;
import com.example.taskmanager.task.dto.TaskResponse;
import com.example.taskmanager.task.dto.UpdateTaskRequest;
import com.example.taskmanager.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(ApiResponse.notFound("Tasks not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(ApiResponse.success(tasks, "All tasks retrieved successfully."), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(ApiResponse.notFound("Task not found with id : " + id), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(ApiResponse.success(task, "Task with id : " + id + " retrieved successfully."), HttpStatus.OK);

        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse task = taskService.createTask(request);
        return new ResponseEntity<>(ApiResponse.success(task, "Task created successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        TaskResponse updatedTask =  taskService.updateTask(id, request);
        return new ResponseEntity<>(ApiResponse.success(updatedTask, "Task updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
