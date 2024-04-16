package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

@Getter
public enum Emoji {
    HAPPY(0), NEUTRAL(1), UNHAPPY(2);

    private final int value;

    private Emoji(int value) {
        this.value = value;
    }

    public static Emoji from(Integer value) {
        for (Emoji emoji : Emoji.values()) {
            if (emoji.value == value) {
                return emoji;
            }
        }
        throw new IllegalArgumentException("범위 내의 이모지가 아닙니다");
    }
}
