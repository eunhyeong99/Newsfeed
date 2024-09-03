package com.team24.newsfeed.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NewsfeedException extends RuntimeException {

    private final HttpStatus httpStatus;

    public NewsfeedException(NewsfeedExceptionConst newsfeedExceptionConst) {
        super(newsfeedExceptionConst.name() + newsfeedExceptionConst.getMessage());
        this.httpStatus = newsfeedExceptionConst.getHttpStatus();
    }
}
