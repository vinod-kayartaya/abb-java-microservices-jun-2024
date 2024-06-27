package com.abb.customerservice.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(e -> errors.put(e.getField().toString(), e.getDefaultMessage()));
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity handleDataAccessException(DataAccessException ex) {
        ErrorData err = new ErrorData("either email or phone is duplicate");
        // return ResponseEntity.status(400).body(err);
        return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
    }

}
