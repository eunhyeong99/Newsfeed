package com.team24.newsfeed.exception;

public class DuplicateSubscribeException extends RuntimeException{
    private String detailMessage;

    public DuplicateSubscribeException(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public String getDetailMessage(){
        return detailMessage;
    }
}