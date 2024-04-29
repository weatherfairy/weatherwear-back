package com.weatherfairy.weatherwearback.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long accessExpireMinutes;
    private final long refreshExpireDays;
    private final String issuer;

    /**
     * 지정된 시크릿 키, 액세스 토큰 만료 시간, 리프레시 토큰 만료 시간 및 발행자로 JwtTokenProvider를 구성합니다.
     *
     * @param secretKey          토큰 서명에 사용되는 시크릿 키
     * @param accessExpireMinutes  액세스 토큰의 만료 시간(분)
     * @param refreshExpireDays    리프레시 토큰의 만료 시간(일)
     * @param issuer             토큰 발행자
     */
    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration.access}") long accessExpireMinutes,
            @Value("${jwt.expiration.refresh}") long refreshExpireDays,
            @Value("${jwt.issuer}") String issuer
    ) {
        this.secretKey = secretKey;
        this.accessExpireMinutes = accessExpireMinutes;
        this.refreshExpireDays = refreshExpireDays;
        this.issuer = issuer;
    }

    /**
     * 액세스 토큰을 생성합니다.
     *
     * @param accessTokenInfo 멤버 정보를 담고 있는 객체
     * @return 생성된 액세스 토큰
     */
    public String createAccessToken(AccessTokenInfo accessTokenInfo) {
        LocalDateTime now = LocalDateTime.now();

        return Jwts.builder()
                        .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                        .setSubject(String.valueOf(accessTokenInfo.memberNo()))
                        .setIssuer(issuer)
                        .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                        .setExpiration(Date.from(now.plusMinutes(accessExpireMinutes).atZone(ZoneId.systemDefault()).toInstant()))
                        .compact();
    }

    /**
     * 리프레시 토큰을 재생성합니다.
     *
     * @return 생성된 리프레시 토큰
     */
    public String createRefreshToken(RefreshTokenInfo refreshTokenInfo) {
        LocalDateTime now = LocalDateTime.now();

        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setSubject(refreshTokenInfo.kakaoId())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(now.plusDays(refreshExpireDays).atZone(ZoneId.systemDefault()).toInstant()))
                .setIssuer(issuer)
                .compact();
    }

    /**
     * 리프레시 토큰을 사용하여 새로운 액세스 토큰을 생성합니다.
     *
     * @param refreshToken 리프레시 토큰
     * @return 새로 생성된 액세스 토큰
     * @throws RuntimeException 리프레시 토큰이 잘못된 경우 예외가 발생합니다.
     */
    public String renewAccessToken(String refreshToken) {
        // 리프레시 토큰의 유효성 검증
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        // 리프레시 토큰이 유효한 경우, 새로운 액세스 토큰 생성
        if (claims.getIssuer().equals(issuer)) {
            LocalDateTime now = LocalDateTime.now();

            return Jwts.builder()
                    .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                    .setSubject(claims.getSubject())
                    .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                    .setExpiration(Date.from(now.plusMinutes(accessExpireMinutes).atZone(ZoneId.systemDefault()).toInstant()))
                    .compact();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * HTTP 요청에서 액세스 토큰을 추출합니다.
     *
     * @param request HTTP 요청
     * @return 추출된 액세스 토큰
     */
    public String extractAccessTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null) {
            throw new IllegalArgumentException();
        }

        return bearerToken;
    }


    /**
     * HTTP 요청에서 리프레시 토큰을 추출합니다.
     *
     * @param request HTTP 요청
     * @return 추출된 리프레시 토큰
     */
    public String extractRefreshTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Refresh-Token");
    }


    /**
     * 액세스 토큰의 유효성을 검사합니다.
     *
     * @param token 검사할 액세스 토큰
     * @throws IllegalArgumentException 토큰이 유효하지 않은 경우 예외가 발생합니다.
     */
    public void validateAccessToken(String token) throws IllegalArgumentException {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token.substring(7));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 리프레시 토큰의 유효성을 검사합니다.
     *
     * @param token 검사할 리프레시 토큰
     */
    public void validateRefreshToken(String token) throws IllegalArgumentException {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 액세스 토큰에서 memberNo를 추출합니다.
     *
     * @param token 데이터를 가져올 액세스 토큰
     * @return 추출된 memberNo
     */
    public Long getMemberNoFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

}

