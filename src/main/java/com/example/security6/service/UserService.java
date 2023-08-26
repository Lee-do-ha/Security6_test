package com.example.security6.service;

import com.example.security6.config.BcryptEncoder;
import com.example.security6.config.EncoderConfig;
import com.example.security6.domain.dto.user.RequestDto;
import com.example.security6.domain.entity.User;
import com.example.security6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncoderConfig encoderConfig;

    public void save(RequestDto requestDto){

        // 회원가입 아이디 중복 방지
        Optional<User> optionalMember = userRepository.findByUserId(requestDto.getUserId());

        if(!optionalMember.isPresent()){
            throw new RuntimeException("사용 할 수 없는 아이디입니다.");
        }

        User user = new User().builder()
                .userId(requestDto.getUserId())
                .userName(requestDto.getUserName())
                .userPassword(encoderConfig.EncoderPassword(requestDto.getUserPassword()))
                .build();

        userRepository.save(user);

    }
}
