package com.example.taskmanager.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum RoleDto {
    ADMIN,
    USER;

    @JsonCreator
    public static RoleDto fromString(String value) {
        return Arrays.stream(RoleDto.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid user role : " + value));
    }
}
