package com.example.security6.domain.dto.board;

import lombok.Getter;

public class JoinDto {

    private String title;
    private String content;
    private Long view;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
