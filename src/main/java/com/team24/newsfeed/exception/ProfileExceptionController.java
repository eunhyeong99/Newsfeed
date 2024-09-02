package com.team24.newsfeed.exception;

import com.team24.newsfeed.controller.ProfileController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ProfileController.class)
public class ProfileExceptionController {

    private static final String PASSWORD_MISMATCH = "PASSWORD_MISMATCH";
    private static final String SAME_PASSWORD = "SAME_PASSWORD";
    private static final String INVALID_PASSWORD_PATTERN = "INVALID_PASSWORD_PATTERN";

    @ExceptionHandler
    public ResponseEntity<ErrorResult> notSamePasswordException(NotSamePasswordException e) {
        return new ResponseEntity<>(new ErrorResult(PASSWORD_MISMATCH, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> sameUpdatePasswordException(SameUpdatePasswordException e) {
        return new ResponseEntity<>(new ErrorResult(SAME_PASSWORD, e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> invalidPasswordPatternException(InvalidPasswordPatternException e) {
        return new ResponseEntity<>(new ErrorResult(INVALID_PASSWORD_PATTERN, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
