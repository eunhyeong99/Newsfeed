package com.team24.newsfeed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NewsfeedExceptionHandler {

    // newsfeedException을 상속받은
    @ExceptionHandler(NewsfeedException.class)
    public ResponseEntity<ErrorResult> handlerNewsfeedException(NewsfeedException e) {
        return new ResponseEntity<>(new ErrorResult(e.getMessage()), e.getHttpStatus());
    }
}
