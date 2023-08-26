package com.example.security6.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum Errorcode {

    USERID_DUPLICATED(HttpStatus.CONFLICT, "중복된 아이디입니다.")
    ;

    private HttpStatus httpStatus;
    private String message;
}
