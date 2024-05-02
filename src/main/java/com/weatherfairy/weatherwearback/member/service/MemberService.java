package com.weatherfairy.weatherwearback.member.service;

import com.weatherfairy.weatherwearback.member.dto.MemberCommandResponse;
import com.weatherfairy.weatherwearback.member.dto.RegistMemberRequest;
import com.weatherfairy.weatherwearback.member.entity.Member;
import com.weatherfairy.weatherwearback.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Member findMemberByNo(Long memberNo) {
        return memberRepository.findById(memberNo)
                .orElseThrow(EntityNotFoundException::new);
    }

    //Email 인증 후 회원가입
    @Transactional
    public MemberCommandResponse signup(RegistMemberRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        memberRepository.save(member);

        return MemberCommandResponse.from(member);

    }



}
