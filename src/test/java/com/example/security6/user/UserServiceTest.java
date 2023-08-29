package com.example.security6.user;


import com.example.security6.domain.dto.user.JoinDto;
import com.example.security6.domain.dto.user.LoginDto;
import com.example.security6.exception.AppException;
import com.example.security6.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("회원가입 실패 - 아이디 중복")
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

        System.out.println("첫번째 회원 회원가입 성공");

        Assert.assertThrows(AppException.class, () -> {
            userService.save(newJoinDto);
        });

    }

    @Test
    @DisplayName("로그인 성공")
    public void 로그인_성공(){

        JoinDto joinDto = JoinDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .userName("testName")
                .build();

        userService.save(joinDto);

        LoginDto loginDto = LoginDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .build();

        userService.login(loginDto);
    }

    @Test
    @DisplayName("로그인 실패 - 없는 아이디")
    public void 로그인_실패_없는_아이디(){

        LoginDto loginDto = LoginDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .build();

        Assert.assertThrows(AppException.class, () -> {
            userService.login(loginDto);
        });

    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 오류")
    public void 로그인_실패_비밀번호_오류(){

        JoinDto joinDto = JoinDto.builder()
                .userId("testId")
                .userPassword("testPassword")
                .userName("testName")
                .build();

        userService.save(joinDto);

        LoginDto loginDto = LoginDto.builder()
                .userId("testId")
                .userPassword("invalidPassword")
                .build();

        Assert.assertThrows(AppException.class, () -> {
            userService.login(loginDto);
        });

    }
}
