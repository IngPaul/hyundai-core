package com.hyundai.challenge.configuration.handler.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

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
    private ResponseEntity<Map<String, String>> handler(Exception exception, HttpStatus httpStatus){
        Map<String, String> errorMap = new HashMap<>();
        exception.printStackTrace();
        String msgException=exception.getMessage();
        String msg = msgException.substring(msgException.indexOf("\"") + 1, msgException.lastIndexOf("\""));
        errorMap.put("error message",msg);
        errorMap.put("status", httpStatus.toString());

        return ResponseEntity.status(httpStatus).body(errorMap);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handlerGenericException(Exception exception){
        return handler(exception, HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handlerResponseStatusException(ResponseStatusException exception){
        return handler(exception,exception.getStatus()) ;
    }

}