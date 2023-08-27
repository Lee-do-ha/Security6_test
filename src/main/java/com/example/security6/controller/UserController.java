package com.example.security6.controller;

import com.example.security6.domain.dto.user.JoinDto;
import com.example.security6.domain.dto.user.LoginDto;
import com.example.security6.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDto joinDto){

        userService.save(joinDto);

        return ResponseEntity.ok().body("회원가입 성공");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        String token = userService.login(loginDto);

        log.info("로그인 성공");

        return ResponseEntity.ok().body("로그인 성공 " + token);
    }

}
