package com.example.security6.controller;

import com.example.security6.domain.dto.member.RequestDto;
import com.example.security6.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody RequestDto requestDto){

        memberService.save(requestDto);

        return ResponseEntity.ok().body("회원가입 성공");
    }

}
