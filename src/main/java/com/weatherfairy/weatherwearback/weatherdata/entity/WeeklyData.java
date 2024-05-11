package com.weatherfairy.weatherwearback.weatherdata.entity;

import com.weatherfairy.weatherwearback.common.converter.IntListConverter;
import com.weatherfairy.weatherwearback.common.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_WEEKLY")
public  class WeeklyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weeklyId;

    @Column
    @Comment("장소 이름")
    private String locationName;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> minTemp;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> maxTemp;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> dayRain;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> nightRain;

    @Column(length = 1000)
    @Convert(converter = IntListConverter.class)
    private List<Integer> daySky;

    @Column(length = 1000)
    @Convert(converter = IntListConverter.class)
    private List<Integer> nightSky;

    @Builder
    public WeeklyData(String locationName, List<String> minTemp, List<String> maxTemp, List<String> dayRain, List<String> nightRain, List<Integer> daySky, List<Integer> nightSky) {
        this.locationName = locationName;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dayRain = dayRain;
        this.nightRain = nightRain;
        this.daySky = daySky;
        this.nightSky = nightSky;
    }
}
