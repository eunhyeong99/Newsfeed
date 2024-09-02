package com.team24.newsfeed.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResult {

    private final String code;
    private final String message;
}
