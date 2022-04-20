package com.example.demo.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(ApiException exc, WebRequest req){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .details(req.getDescription(false))
                .message(exc.getMessage())
                .status(exc.getStatus())
                .build();
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }
}
