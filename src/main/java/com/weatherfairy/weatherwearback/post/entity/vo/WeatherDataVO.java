package com.weatherfairy.weatherwearback.post.entity.vo;

import com.weatherfairy.weatherwearback.common.enums.SkyCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeatherDataVO implements Serializable {

    @Column
    private float minTemp;

    @Column
    private float maxTemp;

    @Column
    private SkyCategory sky;

    @Column
    private TempCategory tempCategory;

    @Builder
    public WeatherDataVO(float minTemp, float maxTemp, SkyCategory sky, TempCategory tempCategory) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.sky = sky;
        this.tempCategory = tempCategory;
    }
}
