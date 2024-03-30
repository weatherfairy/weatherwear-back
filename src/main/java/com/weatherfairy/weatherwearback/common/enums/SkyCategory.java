package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

@Getter
public enum SkyCategory {

    SUNNY(1), OVERCAST(2), CLOUDY(3), SLEET(4), RAIN(5), SNOW(6), WINDY(7);

    private final int value;

    private SkyCategory(int value) {
        this.value = value;
    }

    public static SkyCategory from(int value) {
        for (SkyCategory category : SkyCategory.values()) {
            if (category.value == value) {
                return category;
            }
        }
        throw new IllegalArgumentException("범위 내의 카테고리가 아닙니다");
    }
}
