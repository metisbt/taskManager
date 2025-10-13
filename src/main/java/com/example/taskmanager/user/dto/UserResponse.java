package com.example.taskmanager.user.dto;

import com.example.taskmanager.user.model.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;
    private String userName;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
