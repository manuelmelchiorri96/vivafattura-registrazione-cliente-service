package com.vivafattura.controller;

import com.vivafattura.exception.ErrorMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ClienteRegistrazioneAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException ex) {

        // Logica per estrarre i messaggi di errore dalla BindException
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorMessage errorDetails = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errorMessage, null);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> dataIntegrityViolation(DataIntegrityViolationException ex) {

        // Logica per gestire le eccezioni di violazione di integrità dei dati
        ErrorMessage errorDetails = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), Objects.requireNonNull(ex.getRootCause()).getMessage(), null);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> constraintViolation(ConstraintViolationException ex) {

        // Logica per gestire le eccezioni di violazione di vincoli
        String errorMessage = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        ErrorMessage errorDetails = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errorMessage, null);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
