package com.weatherfairy.weatherwearback.post.entity;

import com.weatherfairy.weatherwearback.common.enums.Emoji;
import com.weatherfairy.weatherwearback.post.entity.vo.WeatherDataVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "TBL_POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private Long memberNo;

    @Column
    private LocalDate date;

    @Column
    private String image1;

    @Column
    private String image2;

    @Column
    private String image3;

    @Column
    private String clothes;

    @Column
    private String review;

    @Column
    private Emoji emoji;

    @Embedded
    private WeatherDataVO weatherDataVO;

    @Builder
    public Post(Long memberNo, LocalDate date, String image1, String image2, String image3, String clothes, String review, Emoji emoji, WeatherDataVO weatherDataVO) {
        this.memberNo = memberNo;
        this.date = date;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.clothes = clothes;
        this.review = review;
        this.emoji = emoji;
        this.weatherDataVO = weatherDataVO;
    }
}
