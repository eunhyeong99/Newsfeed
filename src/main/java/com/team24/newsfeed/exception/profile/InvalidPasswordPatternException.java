package com.team24.newsfeed.exception.profile;

import com.team24.newsfeed.exception.NewsfeedException;

import static com.team24.newsfeed.exception.NewsfeedExceptionConst.INVALID_PASSWORD_PATTERN;

public class InvalidPasswordPatternException extends NewsfeedException {

    public InvalidPasswordPatternException() {
        super(INVALID_PASSWORD_PATTERN);
    }

}
