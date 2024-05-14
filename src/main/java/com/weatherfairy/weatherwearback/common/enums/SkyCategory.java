package com.weatherfairy.weatherwearback.common.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum SkyCategory {

    THUNDERSTORM(2), DRIZZLE(3), RAIN(5), SNOW(6), OVERCAST(7), SUNNY(8);

    private final int value;

    private SkyCategory(int value) {
        this.value = value;
    }

    public static SkyCategory from(Integer value) {
        for (SkyCategory category : SkyCategory.values()) {
            if (category.value == value) {
                return category;
            }
        }
        throw new IllegalArgumentException("범위 내의 카테고리가 아닙니다");
    }

    public static List<SkyCategory> fromList(List<Integer> value) {
        List<SkyCategory> skyCategories = new ArrayList<>();
        for (Integer intValue : value) {
            SkyCategory skyCategory = SkyCategory.from(intValue);
            if (skyCategory != null) {
                skyCategories.add(skyCategory);
            } else {
                throw new IllegalArgumentException("범위 내의 이모지가 아닙니다");
            }
        }
        return skyCategories;
    }

}
