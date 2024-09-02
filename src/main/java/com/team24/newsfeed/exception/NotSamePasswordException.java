package com.team24.newsfeed.exception;

public class NotSamePasswordException extends RuntimeException{

    public NotSamePasswordException(String message) {
        super(message);
    }
}
