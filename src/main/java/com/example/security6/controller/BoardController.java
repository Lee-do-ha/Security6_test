package com.example.security6.controller;

import com.example.security6.domain.dto.board.BoardDto;
import com.example.security6.domain.dto.board.JoinDto;
import com.example.security6.domain.entity.Board;
import com.example.security6.service.BoardService;
import com.example.security6.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestBody JoinDto joinDto, Authentication authentication){

        boardService.save(joinDto, authentication.getName());

        return ResponseEntity.ok().body("글 작성 완료");
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> list(){

        List<Board> list = boardService.getList();
        List<BoardDto> boardDTOs = list.stream().map(BoardDto::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(boardDTOs);
    }

    @GetMapping("/mylist")
    public ResponseEntity<List<Board>> getMyList(Authentication authentication){

        List<Board> list = boardService.getMyList(authentication.getName());

        return ResponseEntity.ok().body(list);
    }
}
