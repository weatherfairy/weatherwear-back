package com.weatherfairy.weatherwearback.member.dto;

public record RegistMemberRequest(
        String email,
        String password,
        String code
) {
}
