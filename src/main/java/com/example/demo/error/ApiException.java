package com.example.demo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{
    private final HttpStatus status;
    private final String name;

    public ApiException(HttpStatus status, String message, String name) {
        super(message);
        this.status = status;
        this.name = name;
    }
}
