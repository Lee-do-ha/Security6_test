package com.example.security6.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class BcryptEncoder {

    // 비밀번호 암호화
    public String EncoderPassword(String password){

        String EncodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        log.info("암호화된 비밀번호 : " + EncodedPassword);

        return EncodedPassword;
    }

    // 로그인 시에 입력한 비밀번호와 암호화된 비밀번호가 같은지 체크하기 위함
    public boolean checkPassword(String input, String password){

        if(BCrypt.checkpw(input, password)){
            return true;
        }else{
            return false;
        }

    }
}
