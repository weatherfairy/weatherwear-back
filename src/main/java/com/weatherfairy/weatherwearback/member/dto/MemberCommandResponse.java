package com.weatherfairy.weatherwearback.member.dto;

import com.weatherfairy.weatherwearback.member.entity.Member;

public record MemberCommandResponse(
        String email
) {

    public static MemberCommandResponse from(Member member) {
        return new MemberCommandResponse(
                member.getEmail()
        );
    }
}
