package com.example.security6.service;

import com.example.security6.config.EncoderConfig;
import com.example.security6.domain.dto.user.JoinDto;
import com.example.security6.domain.dto.user.LoginDto;
import com.example.security6.domain.entity.User;
import com.example.security6.exception.AppException;
import com.example.security6.exception.Errorcode;
import com.example.security6.repository.UserRepository;
import com.example.security6.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncoderConfig encoderConfig;

    public void save(JoinDto joinDto){

        // 회원가입 아이디 중복 방지
        Optional<User> optionalMember = userRepository.findByUserId(joinDto.getUserId());

        if(optionalMember.isPresent()){
            throw new AppException(Errorcode.USER_ID_DUPLICATED, "사용할 수 없는 아이디입니다.");
        }

        User user = new User().builder()
                .userId(joinDto.getUserId())
                .userName(joinDto.getUserName())
                .userPassword(encoderConfig.EncoderPassword(joinDto.getUserPassword()))
                .build();

        userRepository.save(user);

    }

    public void login(LoginDto loginDto){

        Optional<User> optionalUser = userRepository.findByUserId(loginDto.getUserId());

        // 회원가입된 아이디인지 확인
        if(!optionalUser.isPresent()){
            throw new AppException(Errorcode.USER_ID_NOT_FOUND, "아이디를 다시 확인해주세요");
        }

        // 비밀번호 확인
        if(!BCrypt.checkpw(loginDto.getUserPassword(), optionalUser.get().getUserPassword())){
            throw new AppException(Errorcode.USER_PASSWORD_ERROR, "비밀번호를 다시 확인해주세요");
        }

    }
}
