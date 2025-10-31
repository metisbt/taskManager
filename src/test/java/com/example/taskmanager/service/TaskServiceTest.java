package com.example.taskmanager.service;

import com.example.taskmanager.task.model.Task;
import com.example.taskmanager.task.model.TaskDao;
import com.example.taskmanager.task.service.TaskServiceImpl;
import com.example.taskmanager.user.model.Role;
import com.example.taskmanager.user.model.User;
import com.example.taskmanager.user.service.UserService;
import com.example.taskmanager.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private TaskDao taskDao;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    void shouldCreateTaskSuccessfully() {
        User user = new User();
        user.setId(1L);
        user.setUserName("mahdi");
        user.setPassword("pass");
        user.setRole(Role.USER);

        Task task = new Task();
        task.setTitle("Test Task");

        when(taskServiceImpl.getCurrentUser()).thenReturn(user);
        when(taskDao.save(any(Task.class))).thenReturn(task);

    }
}
