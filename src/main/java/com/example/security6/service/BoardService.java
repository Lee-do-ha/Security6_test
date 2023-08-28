package com.example.security6.service;

import com.example.security6.domain.dto.board.JoinDto;
import com.example.security6.domain.entity.Board;
import com.example.security6.exception.AppException;
import com.example.security6.exception.Errorcode;
import com.example.security6.repository.BoardRepository;
import com.example.security6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(JoinDto joinDto, String userId){

        Board board = Board.builder()
                .user(userRepository.findByUserId(userId).get())
                .boardContent(joinDto.getContent())
                .boardTitle(joinDto.getTitle())
                .boardView(0L)
                .build();

        boardRepository.save(board);

    }

    public List<Board> getList(){

        List<Board> list = boardRepository.findAll();

        if(list.isEmpty()){
            throw new AppException(Errorcode.BOARD_LIST_NOT_FOUND, " 게시글이 존재하지 않습니다.");
        }

        return list;
    }

    public List<Board> getMyList(String userId){

        List<Board> list = userRepository.findByUserId(userId).get().getBoardList();

        return list;
    }

}
