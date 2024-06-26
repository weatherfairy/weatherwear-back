package com.weatherfairy.weatherwearback.yesterday.entity;

import com.weatherfairy.weatherwearback.common.converter.FloatListConverter;
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
@Table(name = "TBL_YESTERDAY")
public class Yesterday{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int yesterdayId;

    @Column
    @Comment("장소 이름")
    private String locationName;

    @Column(length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> temperature;

    @Column
    @Convert(converter = IntListConverter.class)
    private List<Integer> skyCategory;


    @Builder
    public Yesterday(String locationName, List<String> temperature, List<Integer> skyCategory) {
        this.locationName = locationName;
        this.temperature = temperature;
        this.skyCategory = skyCategory;
    }

}
