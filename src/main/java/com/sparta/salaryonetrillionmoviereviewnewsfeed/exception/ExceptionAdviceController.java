package com.sparta.salaryonetrillionmoviereviewnewsfeed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handlerException(CustomException e) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode());

        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }
}