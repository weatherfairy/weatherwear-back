package com.weatherfairy.weatherwearback.member.controller;

import com.weatherfairy.weatherwearback.common.auth.JwtTokenProvider;
import com.weatherfairy.weatherwearback.common.auth.TokenResponse;
import com.weatherfairy.weatherwearback.member.service.KakaoOAuthService;
import com.weatherfairy.weatherwearback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final KakaoOAuthService kakaoOauthService;
    private final JwtTokenProvider jwtTokenProvider;


    @RequestMapping("/api/v1/login/kakao")
    public ResponseEntity<Void> kakaoLogin() {

        URI kakaoUri = URI.create(kakaoOauthService.getKakaoUri());

        return ResponseEntity.status(HttpStatus.FOUND).location(kakaoUri).build();
    }

    @RequestMapping("/api/v1/login/kakao/callback")
    public ResponseEntity<TokenResponse> kakaoCallback(@RequestParam("code") String code) {

        Map<String, String> tokens = kakaoOauthService.kakaoOauth(code);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", tokens.get("accessToken"));
//        headers.add("Refresh-Token", tokens.get("refreshToken"));

        TokenResponse tokenResponse = new TokenResponse(tokens.get("accessToken"), tokens.get("refreshToken"));

        return ResponseEntity.ok(tokenResponse);
    }

    @PutMapping("/api/v1/unlink/kakao")
    public ResponseEntity<Boolean> kakaoLogout(@RequestHeader(value = "Authorization") String token) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        boolean isActive = kakaoOauthService.unlinkAndDeleteMember(memberNo);

        return ResponseEntity.ok()
                .body(isActive);
    }

}

