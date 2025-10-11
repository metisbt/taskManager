package com.example.taskmanager.task.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum TaskStatusDto {
    ToDo,
    InProgress,
    Suspend,
    Done;

    @JsonCreator
    public static TaskStatusDto fromString(String value) {
        return Arrays.stream(TaskStatusDto.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid coverage type: " + value));
    }
}
