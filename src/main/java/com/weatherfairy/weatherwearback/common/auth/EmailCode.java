package com.weatherfairy.weatherwearback.common.auth;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TBL_CODE")
@NoArgsConstructor
public class EmailCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeNo;

    @Column
    private String email;

    @Column
    private String code;

    @Builder
    public EmailCode(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
