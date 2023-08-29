package com.example.security6.domain.dto.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JoinDto {

    private String userId;
    private String userName;
    private String userPassword;

}
