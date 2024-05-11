package com.weatherfairy.weatherwearback.common.auth;

import com.weatherfairy.weatherwearback.member.entity.Member;
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
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long accessExpireMinutes;
    private final String issuer;


    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration.access}") long accessExpireMinutes,
            @Value("${jwt.issuer}") String issuer
    ) {
        this.secretKey = secretKey;
        this.accessExpireMinutes = accessExpireMinutes;
        this.issuer = issuer;
    }


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

    public String extractAccessTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null) {
            throw new IllegalArgumentException();
        }

        return bearerToken;
    }

    public void validateAccessToken(String token) throws IllegalArgumentException {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token.substring(7));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public Long getMemberNoFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
}

