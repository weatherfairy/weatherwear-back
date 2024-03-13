package com.weatherfairy.weatherwearback.longweather.dto;

import com.weatherfairy.weatherwearback.longweather.entity.LongWeather;
import lombok.*;

public class LongWeatherDto {
    private int date;
    private float minTemp;
    private float maxTemp;
    private int sky;

    public LongWeather toEntity() {
        LongWeather build = LongWeather.builder()
                .minTemp(minTemp)
                .maxTemp(maxTemp)
                .sky(sky)
                .build();
        return build;
    }

    @Builder
    public LongWeatherDto(float minTemp, float maxTemp, int sky) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.sky = sky;
    }
}
