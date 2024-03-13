package com.weatherfairy.weatherwearback.longweather.repository;

import com.weatherfairy.weatherwearback.longweather.entity.LongWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongWeatherRepository extends JpaRepository<LongWeather, Integer> {
}
