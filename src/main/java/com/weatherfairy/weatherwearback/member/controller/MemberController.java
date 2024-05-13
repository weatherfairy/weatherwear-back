package com.weatherfairy.weatherwearback.member.controller;

import com.weatherfairy.weatherwearback.common.auth.EmailCode;
import com.weatherfairy.weatherwearback.common.auth.JwtTokenProvider;
import com.weatherfairy.weatherwearback.member.dto.*;
import com.weatherfairy.weatherwearback.member.service.EmailCodeService;
import com.weatherfairy.weatherwearback.member.service.MailService;
import com.weatherfairy.weatherwearback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private final EmailCodeService emailCodeService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/member/signup/email")
    public ResponseEntity<Boolean> mailAuthentication(@RequestParam("email") String email) {
        try {
            mailService.sendMail(email);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }


    @PostMapping("/member/signup/verify")
    public ResponseEntity<Boolean> verifyEmail(@RequestBody MailAuthRequest request) {

        if(mailService.verifyCode(request.email(), request.code())) {
            emailCodeService.deleteCode(request.email());
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/member/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody RegistMemberRequest request) {

        memberService.signup(request);
        return ResponseEntity.ok(true);

    }

    @PostMapping("/member/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response = memberService.login(request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/member/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization")String token) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        Boolean response = memberService.deleteMember(memberNo);

        if (Boolean.TRUE.equals(response)) return ResponseEntity.ok("SUCCESS");
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR");
        }
    }

    @GetMapping("/member/dummy")
    public ResponseEntity<String> createTestData() {
        String token = memberService.createDummy();
        return ResponseEntity.ok(token);
    }

}

