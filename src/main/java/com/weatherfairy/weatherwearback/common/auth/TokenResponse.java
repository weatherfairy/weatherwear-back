package com.weatherfairy.weatherwearback.common.auth;

import com.weatherfairy.weatherwearback.member.entity.Member;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}