package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum TempCategory {

    WINTER1(0),
    WINTER2(1),
    SPRING1(2),
    SPRING2(3),
    AUTUMN1(4),
    AUTUMN2(5),
    SUMMER1(6),
    SUMMER2(7);

    private final int value;
    private TempCategory(int value) {
        this.value = value;
    }

}
