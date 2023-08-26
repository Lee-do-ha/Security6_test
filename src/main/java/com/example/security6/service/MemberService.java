package com.example.security6.service;

import com.example.security6.config.BcryptEncoder;
import com.example.security6.domain.dto.member.RequestDto;
import com.example.security6.domain.entity.Member;
import com.example.security6.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BcryptEncoder bcryptEncoder;

    public void save(RequestDto requestDto){

        // 회원가입 아이디 중복 방지
        Optional<Member> optionalMember = memberRepository.findByUserId(requestDto.getUserId());

        if(!optionalMember.isPresent()){
            throw new RuntimeException("사용 할 수 없는 아이디입니다.");
        }

        Member member = new Member().builder()
                .userId(requestDto.getUserId())
                .userName(requestDto.getUserName())
                .userPassword(bcryptEncoder.EncoderPassword(requestDto.getUserPassword()))
                .build();

        memberRepository.save(member);

    }
}
