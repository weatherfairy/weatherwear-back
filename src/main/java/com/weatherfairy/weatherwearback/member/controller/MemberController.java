package com.weatherfairy.weatherwearback.member.controller;

import com.weatherfairy.weatherwearback.common.auth.EmailCode;
import com.weatherfairy.weatherwearback.member.dto.*;
import com.weatherfairy.weatherwearback.member.service.EmailCodeService;
import com.weatherfairy.weatherwearback.member.service.MailService;
import com.weatherfairy.weatherwearback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private final EmailCodeService emailCodeService;

    @PostMapping("/member/signup/email")
    public ResponseEntity<Boolean> mailAuthentication(@RequestParam("email") String email) {

        String verifiedCode = mailService.sendMail(email);

        MailAuthResponse response = MailAuthResponse.from(verifiedCode);

        return ResponseEntity.ok(true);
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

}

