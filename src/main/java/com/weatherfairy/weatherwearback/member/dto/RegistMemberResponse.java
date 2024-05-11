package com.weatherfairy.weatherwearback.member.dto;

import com.weatherfairy.weatherwearback.member.entity.Member;

public record RegistMemberResponse(
        String email
) {

    public static RegistMemberResponse from(Member member) {
        return new RegistMemberResponse(
                member.getEmail()
        );
    }
}
