package com.example.rd_log_api.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.rd_log_api.domain.dto.ErrorResponse;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;


public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.createFromException(exception));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handeConstraintViolationException(final MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.createFromException(ex));
    }
}
