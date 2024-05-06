package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

@Getter
public enum Gender {
    FEMALE(0), MALE(1);

    private final int value;

    private Gender(int value) {
        this.value = value;
    }

    public static Gender from(Integer value) {
        for (Gender gender : Gender.values()) {
            if (gender.value == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("범위 내의 성별이 아닙니다");
    }
}
