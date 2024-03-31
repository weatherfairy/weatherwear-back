package com.weatherfairy.weatherwearback.today.entity;

import com.weatherfairy.weatherwearback.common.converter.FloatListConverter;
import com.weatherfairy.weatherwearback.common.converter.IntListConverter;
import com.weatherfairy.weatherwearback.common.enums.ClothesCategory;
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
@Table(name = "TBL_TODAY")
public class Today {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todayId;

    @Column
    @Comment("장소 이름")
    private String locationName;

    @Column
    @Comment("x 좌표")
    private int locationX;

    @Column
    @Comment("y 좌표")
    private int locationY;

    @Column
    private int top;

    @Column
    private int bottom;

    @Column
    private float minTemp;

    @Column
    private float maxTemp;

    @Column
    @Convert(converter = FloatListConverter.class)
    private List<Float> temperature;

    @Column
    @Convert(converter = FloatListConverter.class)
    private List<Float> feelsLike;

    @Column
    @Convert(converter = IntListConverter.class)
    private List<Integer> skyCategory;

    @Column
    @Convert(converter = FloatListConverter.class)
    private List<Float> rain;

    @Builder
    public Today(int top, int bottom, float minTemp, float maxTemp, List<Float> temperature, List<Float> feelsLike, List<Integer> skyCategory, List<Float> rain) {
        this.top = top;
        this.bottom = bottom;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.skyCategory = skyCategory;
        this.rain = rain;
    }
}
