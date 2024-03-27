package com.weatherfairy.weatherwearback.post.entity;

import com.weatherfairy.weatherwearback.common.enums.Emoji;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "TBL_POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long memberNo;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private float minTemp;

    @Column
    private float maxTemp;

    @Column
    private SkyCategory sky;

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

    @Builder
    public Post(Long memberNo, LocalDate date, float minTemp, float maxTemp, SkyCategory sky, String image1, String image2, String image3, String clothes, String review, Emoji emoji) {
        this.memberNo = memberNo;
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.sky = sky;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.clothes = clothes;
        this.review = review;
        this.emoji = emoji;
    }
}
