package com.example.taskmanager.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {
    private String userName;
    private String token;
}
