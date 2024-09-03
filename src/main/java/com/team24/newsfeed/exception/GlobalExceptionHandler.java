package com.team24.newsfeed.exception;

import com.team24.newsfeed.controller.ProfileController;
import com.team24.newsfeed.exception.profileexception.ProfileException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ProfileController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> notSamePasswordException(ProfileException e) {
        return new ResponseEntity<>(new ErrorResult(e.getMessage()), e.getHttpStatus());
    }
}
