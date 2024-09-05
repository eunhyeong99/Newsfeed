package com.team24.newsfeed.exception.profile;

import com.team24.newsfeed.exception.NewsfeedException;

import static com.team24.newsfeed.exception.NewsfeedExceptionConst.SAME_PASSWORD;

public class SameUpdatePasswordException extends NewsfeedException {

    public SameUpdatePasswordException() {
        super(SAME_PASSWORD);
    }
}
