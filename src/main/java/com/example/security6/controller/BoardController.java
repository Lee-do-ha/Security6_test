package com.example.security6.controller;

import com.example.security6.domain.dto.board.JoinDto;
import com.example.security6.domain.entity.Board;
import com.example.security6.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Board>> list(){

        List<Board> list = boardService.getList();

        return ResponseEntity.ok().body(list);
    }

}
