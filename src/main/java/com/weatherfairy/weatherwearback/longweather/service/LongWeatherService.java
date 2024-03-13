package com.weatherfairy.weatherwearback.longweather.service;

import com.weatherfairy.weatherwearback.longweather.repository.LongWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LongWeatherService {
    private final LongWeatherRepository longWeatherRepository;


}
