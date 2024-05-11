package com.weatherfairy.weatherwearback.common.auth;

import com.weatherfairy.weatherwearback.member.entity.Member;

public record AccessTokenInfo(
        Long memberNo
) {
    public static AccessTokenInfo from(Member member) {
        return new AccessTokenInfo(member.getMemberNo());
    }
}

