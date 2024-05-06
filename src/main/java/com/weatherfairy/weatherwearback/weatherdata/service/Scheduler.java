package com.weatherfairy.weatherwearback.weatherdata.service;

import com.weatherfairy.weatherwearback.common.locations.Location;
import com.weatherfairy.weatherwearback.common.locations.LocationRepository;
import com.weatherfairy.weatherwearback.weatherdata.repository.DailyDataRepository;
import com.weatherfairy.weatherwearback.weatherdata.repository.WeeklyDataRepository;
import com.weatherfairy.weatherwearback.yesterday.repository.YesterdayRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final WeatherAPIService weatherAPIService;
    private final ParseWeatherData parseWeatherData;
    private final LocationRepository locationRepository;
    private final WeatherDataService weatherDataService;

    private final YesterdayRepository yesterdayRepository;
    private final DailyDataRepository dailyDataRepository;
    private final WeeklyDataRepository weeklyDataRepository;


//    @Scheduled(cron = "0 30 23 * * *")
    public void saveYesterdayData() {
        weatherDataService.getYesterdayFromToday();
    }


//    @Scheduled(cron = "0 40 23 * * *")
    public void getDailyWeather() throws ParseException, IOException {

        dailyDataRepository.deleteAllInBatch();


//        JSONArray weatherData = weatherAPIService.getDailyWeather(
//                "37.56",
//                "127"
//
//        );
//        parseWeatherData.saveDailyData("서울특별시중구",weatherData);

        List<Location> locations = locationRepository.findAll();
        locations.forEach(location -> {
            try {
                JSONArray weatherData = weatherAPIService.getDailyWeather(
                        location.getLatitude(),
                        location.getLongitude()
                );
                parseWeatherData.saveDailyData(location.getLocationName(),weatherData);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
    }

//    @Scheduled(cron = "0 50 23 * * *")
    public void getWeeklyWeather() throws ParseException, IOException {

        weeklyDataRepository.deleteAllInBatch();

//
//        JSONArray weatherData = weatherAPIService.getWeeklyWeather(
//                   "37.56",
//                 "127"
//
//        );
//        parseWeatherData.saveWeeklyData("서울특별시중구",weatherData);

        List<Location> locations = locationRepository.findAll();
        locations.forEach(location -> {
            try {
                JSONArray weatherData = weatherAPIService.getWeeklyWeather(
                        location.getLatitude(),
                        location.getLongitude()
                );
                parseWeatherData.saveWeeklyData(location.getLocationName(),weatherData);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}

