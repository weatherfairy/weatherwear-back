package com.weatherfairy.weatherwearback.common.auth;

import com.weatherfairy.weatherwearback.member.entity.Member;

public record RefreshTokenInfo(
        String kakaoId
) {
    public static RefreshTokenInfo from(Member member) {
        return new RefreshTokenInfo(member.getKakaoId());
    }
}
