package com.weatherfairy.weatherwearback.longweather.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TBL_LONG_WEATHER")
public class LongWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int date;

    @Column
    private float minTemp; // 최저기온

    @Column
    private float maxTemp; // 최고기온

    @Column
    private int sky; // 날씨

    @Builder
    public LongWeather(float minTemp, float maxTemp, int sky) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.sky = sky;
    }
}