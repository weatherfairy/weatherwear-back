package com.weatherfairy.weatherwearback.member.dto;

public record LoginRequest(
        String email,
        String password
) {
}
