package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<ErrorDetails> globalExceptionHandler(){
        return new ResponseEntity<>(new ErrorDetails(), HttpStatus.BAD_REQUEST);
    }
}
