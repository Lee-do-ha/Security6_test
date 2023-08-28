package com.example.security6.service;

import com.example.security6.domain.dto.board.JoinDto;
import com.example.security6.domain.entity.Board;
import com.example.security6.repository.BoardRepository;
import com.example.security6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void save(JoinDto joinDto, String userId){

        Board board = Board.builder()
                .boardAuthor(userRepository.findByUserId(userId).get())
                .boardContent(joinDto.getContent())
                .boardTitle(joinDto.getTitle())
                .boardView(0L)
                .build();

        boardRepository.save(board);

    }

}
