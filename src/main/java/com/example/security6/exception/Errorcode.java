package com.example.security6.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum Errorcode {

    USER_ID_DUPLICATED(HttpStatus.CONFLICT),
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND),
    USER_PASSWORD_ERROR(HttpStatus.NOT_ACCEPTABLE)
    ;

    private HttpStatus httpStatus;
}
