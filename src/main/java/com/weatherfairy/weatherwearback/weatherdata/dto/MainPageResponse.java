package com.weatherfairy.weatherwearback.weatherdata.dto;

import java.util.List;

public record MainPageResponse(
        String temp,
        String rain,
        String wind,
        Long top,
        Long bottom,
        List<String> temp1,
        List<Integer> sky1,
        List<String> temp2,
        List<Integer> sky2,
        List<String> temp3,
        List<Integer> sky3,
        List<String> temp4,
        List<Integer> sky4,
        List<String> weeklyRainDay,
        List<String> weeklyRainNight,
        List<Integer> weeklySkyDay,
        List<Integer> weeklySkyNight,
        List<String> minTemp,
        List<String> maxTemp
) {}