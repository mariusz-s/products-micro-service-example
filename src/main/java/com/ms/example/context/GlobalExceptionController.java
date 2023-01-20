package com.ms.example.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity<Object> exception(ResourceNotFound exception) {
        log.warn("Company not found exception: {}", exception.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
