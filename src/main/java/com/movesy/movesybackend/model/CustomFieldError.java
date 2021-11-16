package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomFieldError {
    private String field;
    private String message;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleUserMethodFieldErrors(MethodArgumentNotValidException ex) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        final List<CustomFieldError> customFieldErrors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            final String field = fieldError.getField();
            final String message = fieldError.getDefaultMessage();
            final CustomFieldError customFieldError = CustomFieldError.builder().field(field).message(message).build();

            customFieldErrors.add(customFieldError);
        }
        return ResponseEntity.badRequest().body(customFieldErrors);
    }
}