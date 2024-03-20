package com.weatherfairy.weatherwearback.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Table(name = "TBL_POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String location;

    @Column
    private Date updateDate;

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
    private Integer emoji;
}
