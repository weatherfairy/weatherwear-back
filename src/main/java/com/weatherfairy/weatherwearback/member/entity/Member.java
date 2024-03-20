package com.weatherfairy.weatherwearback.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column
    @Comment("계정 아이디")
    private String account;

    @Column
    @Comment("계정 비밀번호")
    private String password;

    @Column(nullable = false)
    @Comment("닉네임")
    private String nickname;

    @Column
    @Comment("카카오 로그인 : id")
    private String kakaoId;

    @Column
    @Comment("카카오 로그인 : 카카오에 설정된 이름")
    private String kakaoName;

    @Column
    @Comment("이메일")
    private String email;

    @Builder
    public Member(String account, String password, String nickname, String kakaoId, String kakaoName, String email) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.kakaoId = kakaoId;
        this.kakaoName = kakaoName;
        this.email = email;
    }
}
