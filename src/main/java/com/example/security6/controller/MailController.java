package com.example.security6.controller;

import com.example.security6.domain.dto.user.MailDto;
import com.example.security6.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/mailConfirm")
    public ResponseEntity<String> mailConfirm(@RequestBody MailDto mailDto) throws Exception{

        String code = mailService.sendSimpleMessage(mailDto.getMail());

        return ResponseEntity.ok().body(code);

    }
}
