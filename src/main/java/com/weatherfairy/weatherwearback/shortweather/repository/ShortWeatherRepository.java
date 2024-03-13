package com.weatherfairy.weatherwearback.shortweather.repository;

import com.weatherfairy.weatherwearback.shortweather.entity.ShortWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortWeatherRepository extends JpaRepository<ShortWeather, Long> {

}