package com.team24.newsfeed.exception.profileexception;

import static com.team24.newsfeed.exception.profileexception.ProfileExceptionConst.*;

public class NotSamePasswordException extends ProfileException {

    public NotSamePasswordException() {
        super(PASSWORD_MISMATCH);
    }
}
