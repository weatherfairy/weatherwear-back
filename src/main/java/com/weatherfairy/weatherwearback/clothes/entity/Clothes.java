package com.weatherfairy.weatherwearback.clothes.entity;

import com.weatherfairy.weatherwearback.common.enums.ClothesCategory;
import com.weatherfairy.weatherwearback.common.enums.SkyCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_CLOTHES")
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clothesId;

    @Column
    @Comment("상의, 하의, 여성하의")
    private ClothesCategory clothesCategory;

    @Column
    @Comment("봄1, 봄2, 여름1, 여름2, 가을1, 가을2, 겨울1, 겨울2")
    private TempCategory tempCategory;

    @Column
    @Comment("맑음, 흐림, 비, 눈, 비_눈, 바람, 구름많음")
    private SkyCategory skyCategory;

    @Column
    private String clothesName;

    @Builder
    public Clothes(ClothesCategory clothesCategory, TempCategory tempCategory, SkyCategory skyCategory, String clothesName) {
        this.clothesCategory = clothesCategory;
        this.tempCategory = tempCategory;
        this.skyCategory = skyCategory;
        this.clothesName = clothesName;
    }
}
