package com.team24.newsfeed.dto.response;

import com.team24.newsfeed.domain.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProfileResponseDto {

    private final String username;
    private final String email;
    private final List<Board> boards;
}
