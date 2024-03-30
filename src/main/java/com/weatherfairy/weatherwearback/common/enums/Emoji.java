package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

@Getter
public enum Emoji {
    HAPPY(1), NEUTRAL(2), UNHAPPY(3);

    private final int value;

    private Emoji(int value) {
        this.value = value;
    }
}
