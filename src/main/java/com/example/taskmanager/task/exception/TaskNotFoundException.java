package com.example.taskmanager.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    private final Long id;

    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " not found");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
