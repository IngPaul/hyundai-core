package com.hyundai.hexchallenge.configuration.handler.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(CoreException.class)
    public ResponseEntity<Map<String, String>> handleBankApiException(CoreException bankApiException){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", bankApiException.getMessage());
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handlerGenericException(Exception exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.printStackTrace();
        errorMap.put("error message", exception.getMessage());
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
    }
}