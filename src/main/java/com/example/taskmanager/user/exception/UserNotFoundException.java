package com.example.taskmanager.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private final String field;
    private final String value;

    public UserNotFoundException(String field,  String value) {
        super(String.format("User with %s '%s' not found", field, value));
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }
    public String getValue() {
        return value;
    }
}
