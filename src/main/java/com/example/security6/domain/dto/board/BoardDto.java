package com.example.security6.domain.dto.board;

import com.example.security6.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private Long memberId; // member_id 추가
    private String boardTitle;
    private String boardContent;
    private Long boardView;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public BoardDto(Board board) {
        this(board.getId(), board.getUser().getId(), board.getBoardTitle(), board.getBoardContent(), board.getBoardView(), board.getCreateDate(), board.getModifyDate());
    }

}
