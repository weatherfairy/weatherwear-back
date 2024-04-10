package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

@Getter
public enum SkyCategory {

    SUNNY(0), OVERCAST(1), CLOUDY(2), SLEET(3), RAIN(4), SNOW(5), WINDY(6);

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
