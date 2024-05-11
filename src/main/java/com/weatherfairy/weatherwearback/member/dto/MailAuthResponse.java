package com.weatherfairy.weatherwearback.member.dto;

public record MailAuthResponse(
        String code
) {
    public static MailAuthResponse from(String verifiedCode) {
        return new MailAuthResponse(verifiedCode);
    }
}
