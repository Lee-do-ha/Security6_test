package com.example.security6.service;

import com.example.security6.config.EncoderConfig;
import com.example.security6.domain.dto.user.JoinDto;
import com.example.security6.domain.dto.user.LoginDto;
import com.example.security6.domain.dto.user.ModifyDto;
import com.example.security6.domain.entity.User;
import com.example.security6.exception.AppException;
import com.example.security6.exception.Errorcode;
import com.example.security6.repository.UserRepository;
import com.example.security6.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EncoderConfig encoderConfig;
    @Value("${security.jwt.secretKey}")
    private String secretKey;
    // 토큰 기간 일주일
    private Long expirationTime = 60 * 60 * 24 * 7L;

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

    public String login(LoginDto loginDto){

        Optional<User> optionalUser = userRepository.findByUserId(loginDto.getUserId());

        // 회원가입된 아이디인지 확인
        if(!optionalUser.isPresent()){
            throw new AppException(Errorcode.USER_ID_NOT_FOUND, "존재하지 않는 아이디입니다.");
        }

        // 비밀번호 확인
        if(!BCrypt.checkpw(loginDto.getUserPassword(), optionalUser.get().getUserPassword())){
            throw new AppException(Errorcode.USER_PASSWORD_ERROR, "옳지 않은 비밀번호 입니다.");
        }

        return JwtUtil.createAccessToken(optionalUser.get().getUserId(), secretKey, expirationTime);

    }

    public void delete(String userId) {

        User user = userRepository.findByUserId(userId).get();

        userRepository.delete(user);

    }

    public void modify(String userId, ModifyDto modifyDto) {

        User user = userRepository.findByUserId(userId).get();

        user.setUserPassword(encoderConfig.EncoderPassword(modifyDto.getUserPassword()));

    }
}
