package com.example.security6.domain.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {

    private String userId;
    private String userPassword;
}
