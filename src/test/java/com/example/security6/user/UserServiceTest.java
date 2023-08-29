package com.example.security6.user;


import com.example.security6.domain.dto.user.JoinDto;
import com.example.security6.exception.AppException;
import com.example.security6.repository.UserRepository;
import com.example.security6.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired private UserService userService;

    @Test
    @DisplayName("회원가입 성공")
    public void 회원가입_성공() {

        JoinDto joinDto = JoinDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .userName("testName")
                .build();

        userService.save(joinDto);
    }

    @Test
    @DisplayName("회원가입 아이디 중복 에러")
    public void 회원가입_아이디_중복_에러(){

        JoinDto joinDto = JoinDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .userName("testName")
                .build();

        JoinDto newJoinDto = JoinDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .userName("testName")
                .build();

        userService.save(joinDto);


        Assert.assertThrows(AppException.class, () -> {
            userService.save(newJoinDto);
        });

    }
}
