package com.sapiens.sapiens.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.sapiens.sapiens.infra.exceptions.AuthException;
import com.sapiens.sapiens.infra.exceptions.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleUserException(AuthException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.internalServerError().body("Erro interno. Tente novamente mais tarde.");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
