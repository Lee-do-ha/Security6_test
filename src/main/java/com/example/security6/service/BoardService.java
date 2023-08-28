package com.example.security6.service;

import com.example.security6.domain.dto.board.JoinDto;
import com.example.security6.domain.entity.Board;
import com.example.security6.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(JoinDto joinDto, String userId){

        Board board = Board.builder()
                .boardAuthor(userId)
                .boardContent(joinDto.getContent())
                .boardTitle(joinDto.getTitle())
                .boardView(0L)
                .build();

        boardRepository.save(board);

    }

}
