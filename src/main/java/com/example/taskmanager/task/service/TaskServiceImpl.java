package com.example.taskmanager.task.service;

import com.example.taskmanager.task.dto.CreateTaskRequest;
import com.example.taskmanager.task.dto.TaskResponse;
import com.example.taskmanager.task.dto.UpdateTaskRequest;
import com.example.taskmanager.task.exception.TaskNotFoundException;
import com.example.taskmanager.task.mapper.TaskMapper;
import com.example.taskmanager.task.model.Task;
import com.example.taskmanager.task.model.TaskDao;
import com.example.taskmanager.user.exception.UserNotFoundException;
import com.example.taskmanager.user.model.User;
import com.example.taskmanager.user.model.UserDao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    private final TaskMapper taskMapper ;

    private final UserDao userDao;

    public TaskServiceImpl(TaskDao taskDao, TaskMapper taskMapper, UserDao userDao) {
        this.taskDao = taskDao;
        this.taskMapper = taskMapper;
        this.userDao = userDao;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskDao.findAll().stream().map(taskMapper::toResponse).toList();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskDao.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUserName(username).orElseThrow(() -> new UserNotFoundException(username, username));
        Task task = taskMapper.toEntity(request);
        task.setCreatedBy(user);
        return taskMapper.toResponse(taskDao.save(task));
    }

    @Override
    @Transactional
    public TaskResponse updateTask (Long id, UpdateTaskRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUserName(username).orElseThrow(() -> new UserNotFoundException(username, username));
        Task task = taskDao.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        Task updateTask = taskMapper.updateTaskFromDto(request, task);
        updateTask.setUpdateBy(user);
        return taskMapper.toResponse(taskDao.save(task));
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskDao.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskDao.delete(task);
    }
}
