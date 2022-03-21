package com.example.spring_test.configurations;

import com.example.spring_test.exceptions.NotAuthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerConfig {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> internalException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>("Server error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object>  notAuthorizedException(NotAuthorizedException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>("Not authorized", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
