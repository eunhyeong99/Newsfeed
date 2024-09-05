package com.team24.newsfeed.exception.profile;

import com.team24.newsfeed.exception.NewsfeedException;

import static com.team24.newsfeed.exception.NewsfeedExceptionConst.*;

public class NotSamePasswordException extends NewsfeedException {

    public NotSamePasswordException() {
        super(PASSWORD_MISMATCH);
    }
}
