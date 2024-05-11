package com.example.rd_log_api.domain.dto;

import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collection;

@Data
public class ErrorResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<ErrorMessage> errors;

    private ErrorResponse(String message) {
        this.message = message;
    }

    private ErrorResponse(String message, Collection<ErrorMessage> errors) {
        this(message);
        this.errors = errors;
    }

    public static ErrorResponse createFromException(NotFoundException ex) {
        String message = "No record of " + ex.getClazz().getSimpleName() + " found for id " + ex.getId();
        return new ErrorResponse(message);
    }


    public static ErrorResponse createFromException(MethodArgumentNotValidException ex) {
        String message = "Entity not found: " + ex.getMessage();
        return new ErrorResponse(message);
    }
}
