package com.example.security6.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;

@Slf4j
public class JwtUtil {

    // 토큰 기간 만료 체크
    public static boolean isExpired(String token, String key){

        try {
            Date expirationDate = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody().getExpiration();

            return expirationDate.before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            return true;
        }
    }

    // 토큰 생성
    public static String createAccessToken(String userId, String key, Long expireTime){

        log.info("토큰 생성 시작");

        // 토큰에 담을 정보
        // claims = 토큰에 저장할 일종의 map
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                // claims 싣기
                .setClaims(claims)
                // 토큰 생성 시간
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 토큰 만료 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                // secretKey로 토큰 암호화
                .signWith(new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .compact();

    }

    // 토큰에서 아이디 정보 꺼내기
    public static String getUserId(String token, String key){

        return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token)
                .getBody()
                .get("userId", String.class);

    }

}
