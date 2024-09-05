package com.team24.newsfeed.exception;

import static com.team24.newsfeed.exception.NewsfeedExceptionConst.UNAUTHORIZED_ACCESS;

public class CustomException extends NewsfeedException {

    public CustomException() {
        super(UNAUTHORIZED_ACCESS);
    }
}