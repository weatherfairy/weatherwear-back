package com.weatherfairy.weatherwearback.weatherdata.controller;

import com.weatherfairy.weatherwearback.weatherdata.dto.MainPageResponse;
import com.weatherfairy.weatherwearback.weatherdata.service.Scheduler;
import com.weatherfairy.weatherwearback.weatherdata.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherDataService weatherDataService;
    private final Scheduler scheduler;

    @GetMapping("/api/v1/weathers")
    public ResponseEntity<MainPageResponse> getMainData(@RequestParam("location") String locationName) throws ParseException, IOException {

        MainPageResponse response = weatherDataService.getMainPageData(locationName);

        return ResponseEntity.ok(response);
    }
}
