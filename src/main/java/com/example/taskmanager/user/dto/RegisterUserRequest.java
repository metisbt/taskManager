package com.example.taskmanager.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "user name is required")
    @Size(min = 3, max = 50)
    private String userName;

    @NotBlank(message = "password is required")
    @Size(min = 3, max = 50)
    private String password;

    @NotNull(message = "User role is required")
    private RoleDto role;

//    @NotBlank(message = "Email is required")
//    @Email
//    private String email;

}
