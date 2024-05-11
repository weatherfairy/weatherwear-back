package com.weatherfairy.weatherwearback.member.dto;

import com.weatherfairy.weatherwearback.common.enums.Gender;

public record RegistMemberRequest(
        String email,
        String password,
        Gender gender
) {
}
