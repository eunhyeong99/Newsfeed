package com.team24.newsfeed.exception.profileexception;

import static com.team24.newsfeed.exception.profileexception.ProfileExceptionConst.INVALID_PASSWORD_PATTERN;

public class InvalidPasswordPatternException extends ProfileException {

    public InvalidPasswordPatternException() {
        super(INVALID_PASSWORD_PATTERN);
    }

}
