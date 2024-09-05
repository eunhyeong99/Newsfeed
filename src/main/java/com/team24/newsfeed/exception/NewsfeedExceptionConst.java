package com.team24.newsfeed.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NewsfeedExceptionConst {

    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "사용자 비밀번호가 일치하지 않습니다."),
    SAME_PASSWORD(HttpStatus.CONFLICT, "비밀번호는 최소 8자 이상이어야 하며, 영문자, 숫자, 특수문자를 포함해야 합니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, "이전 비밀번호랑 같습니다."),

    INVALID_USER(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "해당 게시물에 대한 접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
