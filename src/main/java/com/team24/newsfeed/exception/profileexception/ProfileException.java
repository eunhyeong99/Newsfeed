package com.team24.newsfeed.exception.profileexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProfileException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ProfileException(ProfileExceptionConst profileExceptionConst) {
        super(profileExceptionConst.name() + profileExceptionConst.getMessage());
        this.httpStatus = profileExceptionConst.getHttpStatus();
    }
}
