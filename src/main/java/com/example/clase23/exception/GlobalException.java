package com.example.clase23.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler({ResourceNotFoundException.class})
    private ResponseEntity<String> tratResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler({BadRequest.class})
    private ResponseEntity<String> ex(BadRequest ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
