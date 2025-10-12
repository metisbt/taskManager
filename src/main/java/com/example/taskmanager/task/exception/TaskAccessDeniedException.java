package com.example.taskmanager.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TaskAccessDeniedException extends RuntimeException {
    public TaskAccessDeniedException() {
        super("You do not have permission to access this task.");
    }
}
