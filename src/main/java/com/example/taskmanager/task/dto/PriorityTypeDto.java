package com.example.taskmanager.task.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

import static java.util.Locale.filter;

public enum PriorityTypeDto {
    High,
    Medium,
    Low;

    @JsonCreator
    public static PriorityTypeDto fromString(String value) {
        return Arrays.stream(PriorityTypeDto.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid priority type: " + value));
    }
}
