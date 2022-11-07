package com.shortenURL.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeHandler {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final RuntimeException e) {
        return ResponseEntity
                .status(ErrorCode.BAD_REQUEST.getStatus().value())
                .body(new ErrorResponse(ErrorCode.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}
