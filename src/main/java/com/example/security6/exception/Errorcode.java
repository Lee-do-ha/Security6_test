package com.example.security6.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Errorcode {

    USER_ID_DUPLICATED(HttpStatus.CONFLICT, ""),
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    USER_PASSWORD_ERROR(HttpStatus.CONFLICT, ""),
    BOARD_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "")
    ;

    private HttpStatus httpStatus;
    private String message;
}
