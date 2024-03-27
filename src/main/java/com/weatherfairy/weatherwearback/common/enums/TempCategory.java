package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TempCategory {

    SPRING1("~ 4°C"),
    SPRING2("5°C ~ 8°C"),
    SUMMER1("9°C ~ 11°C"),
    SUMMER2("12°C ~ 16°C"),
    AUTUMN1("17°C ~ 19°C"),
    AUTUMN2("20°C ~ 22°C"),
    WINTER1("23°C ~ 27°C"),
    WINTER2("28°C ~");

    private final String tempRange;

}
