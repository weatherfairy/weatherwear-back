package com.weatherfairy.weatherwearback.member.service;

import com.weatherfairy.weatherwearback.common.auth.AccessTokenInfo;
import com.weatherfairy.weatherwearback.common.auth.JwtTokenProvider;
import com.weatherfairy.weatherwearback.common.auth.PasswordEncoder;
import com.weatherfairy.weatherwearback.member.dto.LoginRequest;
import com.weatherfairy.weatherwearback.member.dto.LoginResponse;
import com.weatherfairy.weatherwearback.member.dto.RegistMemberResponse;
import com.weatherfairy.weatherwearback.member.dto.RegistMemberRequest;
import com.weatherfairy.weatherwearback.member.entity.Member;
import com.weatherfairy.weatherwearback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    //Email 인증 후 회원가입
    @Transactional
    public RegistMemberResponse signup(RegistMemberRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .gender(request.gender())
                .build();

        memberRepository.save(member);

        return RegistMemberResponse.from(member);

    }
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        Member member = memberRepository.findMemberByEmail(request.email())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        return new LoginResponse(jwtTokenProvider.createAccessToken(new AccessTokenInfo(member.getMemberNo())));
    }
}
