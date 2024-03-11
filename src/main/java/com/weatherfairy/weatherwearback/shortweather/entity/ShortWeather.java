package com.weatherfairy.weatherwearback.shortweather.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TBL_SHORT_WEATHER")
public class ShortWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shortWeatherId;

    @Column
    private int date; // 날짜

    @Column
    private int time; // 시간

    @Column
    private float temp; // 기온(섭씨)

    @Column
    private float feelsLike; // 체감기온(섭씨)

    @Column
    private int sky; // 날씨

    @Column
    private int dust; // 미세먼지

    @Column
    private int rain; // 강수량

    @Builder
    public ShortWeather(int date, int time, float temp, float feelsLike, int sky, int dust, int rain) {
        this.date = date;
        this.time = time;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.sky = sky;
        this.dust = dust;
        this.rain = rain;
    }
}
