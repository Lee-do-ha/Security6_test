package com.example.security6.config;

import com.example.security6.service.UserService;
import com.example.security6.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    @Value("${security.jwt.secretKey}")
    private final String SecretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        log.info("HTTPHeaders.AUTHORIZATION = " + authorization);

        if(authorization == null || !authorization.startsWith("Bearer ")){

            log.error("토큰이 없거나 유효하지 않은 토큰입니다.");
            filterChain.doFilter(request, response);
            return ;

        }

        String token = authorization.split(" ")[1];
        log.info("token = " + token);

        // 아이디 추출
        String userId = JwtUtil.getUserId(token, SecretKey);
        log.info("userId = " + userId);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("user")));

        // Detail 싣기
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
