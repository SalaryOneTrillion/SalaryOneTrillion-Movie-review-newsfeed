package com.sparta.salaryonetrillionmoviereviewnewsfeed.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private final HttpStatus status;
    private final String message;

    public ExceptionResponse(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getStatus();
        this.message = exceptionCode.getMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
