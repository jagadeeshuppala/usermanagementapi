package com.spamcollector.controller;

import com.spamcollector.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.spamcollector.model.Error;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Error> handleCourseNotFoundException(RuntimeException excption){
        Error error = new Error(404, excption.getMessage(), "visit SpamCollector help page for troubleshooting");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
