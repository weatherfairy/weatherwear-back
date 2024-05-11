package com.weatherfairy.weatherwearback.common.locations;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String locationName;

    @Column
    private int locationX;

    @Column
    private int locationY;

    @Column
    private String latitude;

    @Column
    private String longitude;

}
