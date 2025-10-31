package com.example.taskmanager.user.service;

import com.example.taskmanager.user.dto.LoginUserRequest;
import com.example.taskmanager.user.dto.RegisterUserRequest;
import com.example.taskmanager.user.dto.UserLoginResponse;
import com.example.taskmanager.user.dto.UserResponse;

public interface UserService {
    UserResponse register(RegisterUserRequest registerUserRequest);
    UserLoginResponse login(LoginUserRequest loginUserRequest);
}
