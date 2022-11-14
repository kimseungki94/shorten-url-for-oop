package com.shortenURL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleCustomException(final RuntimeException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(final Exception e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
