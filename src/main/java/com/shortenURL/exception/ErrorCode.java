package com.shortenURL.exception;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
