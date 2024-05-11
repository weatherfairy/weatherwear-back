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
@Table(name = "TBL_DAILY")
public  class DailyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todayId;

    @Column
    @Comment("장소 이름")
    private String locationName;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> temperature;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> windSpeed;

    @Column
    @Convert(converter = IntListConverter.class)
    private List<Integer> skyCategory;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> rain;

    @Builder
    public DailyData(String locationName, List<String> temperature, List<String> windSpeed, List<Integer> skyCategory, List<String> rain) {
        this.locationName = locationName;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.skyCategory = skyCategory;
        this.rain = rain;
    }

}
