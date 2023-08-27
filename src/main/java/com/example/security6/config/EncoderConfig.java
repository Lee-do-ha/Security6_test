package com.example.security6.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class EncoderConfig {

    // 비밀번호 암호화
    public String EncoderPassword(String password){

        String EncodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        log.info("암호화된 비밀번호 : " + EncodedPassword);

        return EncodedPassword;
    }

}
