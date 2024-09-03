package com.team24.newsfeed.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCreateDto {
    private String title;
    private String contents;

    public BoardCreateDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
