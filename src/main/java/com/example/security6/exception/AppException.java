package com.example.security6.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException{

    private Errorcode errorcode;
    private String string;
}
