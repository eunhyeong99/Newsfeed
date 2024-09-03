package com.team24.newsfeed.exception.profileexception;

import static com.team24.newsfeed.exception.profileexception.ProfileExceptionConst.SAME_PASSWORD;

public class SameUpdatePasswordException extends ProfileException {

    public SameUpdatePasswordException() {
        super(SAME_PASSWORD);
    }
}
