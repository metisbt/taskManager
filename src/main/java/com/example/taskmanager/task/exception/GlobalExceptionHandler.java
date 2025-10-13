package com.example.taskmanager.task.exception;


import com.example.taskmanager.task.dto.ApiResponse;
import com.example.taskmanager.user.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleTaskNotFound (TaskNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.notFound(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTaskStateException.class)
    public ResponseEntity<ApiResponse<Void>> handelInvalidTaskStateException (InvalidTaskStateException ex, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("Invalid Task State", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskAccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handelTaskAccessDeniedException (TaskAccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("Access Denied", ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("An unexpected error occurred.", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(", "));
        return new ResponseEntity<>(ApiResponse.error("Validation failed", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyExistsException (UserAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("User already exists", ex.getMessage()), HttpStatus.CONFLICT);
    }
}
