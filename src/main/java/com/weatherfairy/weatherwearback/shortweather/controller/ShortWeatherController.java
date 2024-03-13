package com.weatherfairy.weatherwearback.shortweather.controller;

import com.weatherfairy.weatherwearback.shortweather.dto.ShortWeatherDto;
import com.weatherfairy.weatherwearback.shortweather.service.ShortWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShortWeatherController {
    private final ShortWeatherService shortWeatherService;

    @GetMapping("/v1/weathers")
    public String getShortWeather() {
        return shortWeatherService.getShortWeather();
    }

    /*@GetMapping("/v1/weathers")
    public String getShortWeather() {
        List<ShortWeatherDto> shortWeatherList = shortWeatherService.getShortWeather();
        StringBuilder responseBuilder = new StringBuilder();

        for (ShortWeatherDto shortWeather : shortWeatherList) {
            responseBuilder.append("Date: ").append(shortWeather.getDate()).append(", ");
            responseBuilder.append("Time: ").append(shortWeather.getTime()).append(", ");
            responseBuilder.append("Temperature: ").append(shortWeather.getTemp()).append("\n");
        }

        return responseBuilder.toString();
    }*/
}
