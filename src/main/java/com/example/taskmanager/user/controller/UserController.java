package com.example.taskmanager.user.controller;

import com.example.taskmanager.security.JwtUtil;
import com.example.taskmanager.task.dto.ApiResponse;
import com.example.taskmanager.user.dto.LoginUserRequest;
import com.example.taskmanager.user.dto.RegisterUserRequest;
import com.example.taskmanager.user.dto.UserLoginResponse;
import com.example.taskmanager.user.dto.UserResponse;
import com.example.taskmanager.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    public final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse registerUser = userService.register(request);
        return new ResponseEntity<>(ApiResponse.success(registerUser, "User registered successfully"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginUser(@Valid @RequestBody LoginUserRequest request) {
        UserLoginResponse loginUser = userService.login(request);
        return new ResponseEntity<>(ApiResponse.success(loginUser, "User logged successfully"), HttpStatus.OK);
    }
}
