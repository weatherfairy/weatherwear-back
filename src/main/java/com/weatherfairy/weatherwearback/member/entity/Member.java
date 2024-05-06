package com.weatherfairy.weatherwearback.member.entity;

import com.weatherfairy.weatherwearback.common.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Table(name = "TBL_MEMBER")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Gender gender;


    @Builder
    public Member(String email,  String password, Gender gender) {
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

}
