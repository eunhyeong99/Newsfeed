package com.team24.newsfeed.exception;

public class SameUpdatePasswordException extends RuntimeException{

    public SameUpdatePasswordException(String message) {
        super(message);
    }
}
