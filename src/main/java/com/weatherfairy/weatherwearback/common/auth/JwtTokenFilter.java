package com.weatherfairy.weatherwearback.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        try {
            if (shouldNotFilter(request)) {
                filterChain.doFilter(request, response);
            } else {
                String accessToken = jwtTokenProvider.extractAccessTokenFromRequest(request);
                jwtTokenProvider.validateAccessToken(accessToken);
                filterChain.doFilter(request, response);
            }
        } catch (ExpiredJwtException e) {
            handleExpiredAccessToken(request, response, filterChain);
        } catch (IllegalArgumentException e) {
            handleExceptionWithErrorCode(response, 501);
        } catch (Exception e) {
            handleExceptionWithErrorCode(response, 500);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String[] excludePath = {"/login/kakao", "/api/v1"};
        String path = request.getRequestURI();

        return Arrays.stream(excludePath).anyMatch(path::contains);
    }

    private void handleExpiredAccessToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String refreshToken = jwtTokenProvider.extractRefreshTokenFromRequest(request);
        try {
            jwtTokenProvider.validateRefreshToken(refreshToken);
            String newAccessToken = jwtTokenProvider.renewAccessToken(refreshToken);
            response.setHeader("Authorization", "Bearer " + newAccessToken);
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException ex) {
            handleExceptionWithErrorCode(response, 501);
        }
    }

    private void handleExceptionWithErrorCode(HttpServletResponse response, int errorCode) throws IOException {
        // json 데이터 형식 지정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // 날짜 및 시간을 ISO-8601 형식으로 설정
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonResult = objectMapper.writeValueAsString(errorCode);
        response.getWriter().write(jsonResult);
    }
}


