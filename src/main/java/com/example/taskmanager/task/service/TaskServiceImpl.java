package com.example.taskmanager.task.service;

import com.example.taskmanager.task.dto.CreateTaskRequest;
import com.example.taskmanager.task.dto.TaskResponse;
import com.example.taskmanager.task.dto.UpdateTaskRequest;
import com.example.taskmanager.task.mapper.TaskMapper;
import com.example.taskmanager.task.model.Task;
import com.example.taskmanager.task.model.TaskDao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    private final TaskMapper taskMapper ;

    public TaskServiceImpl(TaskDao taskDao, TaskMapper taskMapper) {
        this.taskDao = taskDao;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskDao.findAll().stream().map(taskMapper::toResponse).toList();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        return taskMapper.toResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = taskMapper.toEntity(request);
        return taskMapper.toResponse(taskDao.save(task));
    }

    @Override
    @Transactional
    public TaskResponse updateTask (Long id, UpdateTaskRequest request) {
        Task task = taskDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        taskMapper.updateTaskFromDto(request, task);
        return taskMapper.toResponse(taskDao.save(task));
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        taskDao.delete(task);
    }

    @Override
    public TaskResponse getTaskByTitle(String title) {
        Task task = taskDao.findByTitle(title);
        return taskMapper.toResponse(task);
    }
}
