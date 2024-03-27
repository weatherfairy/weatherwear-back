package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

@Getter
public enum Emoji {
    HAPPY(0), NEUTRAL(1), UNHAPPY(2);

    private final int value;

    private Emoji(int value) {
        this.value = value;
    }
}
