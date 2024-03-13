package com.weatherfairy.weatherwearback.shortweather.dto;

import com.weatherfairy.weatherwearback.shortweather.entity.ShortWeather;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ShortWeatherDto {
    private Long shortWeatherId;
    private int date;
    private int time;
    private float temp;
    private float feelsLike;
    private int sky;
    private int dust;
    private int rain;

    public ShortWeather toEntity() {
        ShortWeather build = ShortWeather.builder()
                .date(date)
                .time(time)
                .temp(temp)
                .feelsLike(feelsLike)
                .sky(sky)
                .dust(dust)
                .rain(rain)
                .build();
        return build;
    }

    @Builder
    public ShortWeatherDto(int date, int time, float temp, float feelsLike, int sky, int dust, int rain) {
        this.date = date;
        this.time = time;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.sky = sky;
        this.dust = dust;
        this.rain = rain;
    }
}
