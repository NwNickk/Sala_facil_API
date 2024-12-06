package com.sala.aplicativop.config;

import com.sala.aplicativop.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        String errorMessage = "Erro de validação nos campos: " + errors.toString();
        RestErrorMessage restError = new RestErrorMessage(HttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(restError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleInvalidFormatException(HttpMessageNotReadableException ex) {
        String fieldError = "Campo desconhecido";
        String errorMessage = "Formato de entrada inválido: " + fieldError + ". Verifique os campos e tente novamente.";
        RestErrorMessage restError = new RestErrorMessage(HttpStatus.BAD_REQUEST, errorMessage);

        return new ResponseEntity<>(restError, HttpStatus.BAD_REQUEST);
    }
}
