package com.weatherfairy.weatherwearback.common.weatherdata;

import com.weatherfairy.weatherwearback.today.entity.Today;
import com.weatherfairy.weatherwearback.yesterday.entity.Yesterday;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
@Configuration
@RequiredArgsConstructor
public class Scheduler {

    private final WeatherAPIService weatherAPIService;
    private final ParseWeatherData parseWeatherData;

    @Scheduled(cron = "0 30 22 * * *")
    public void saveYesterdayData() throws ParseException, IOException {
        LocalDate date = LocalDate.now().plusDays(1);
        JSONArray tomorrowJson = weatherAPIService.getWeatherData(date.toString().replace("-", ""));
        parseWeatherData.saveYesterdayWeather(tomorrowJson);


    }
    @Scheduled(cron = "0 0 23 * * *")
    public void saveTodayData() throws ParseException, IOException {
        LocalDate date = LocalDate.now().minusDays(1);
        JSONArray todayJson = weatherAPIService.getWeatherData(date.toString().replace("-", ""));
        parseWeatherData.saveTodayWeather(todayJson);
    }

    @Scheduled(cron = "0 30 23 * * *")
   public void saveTomorrowData() throws ParseException, IOException{
        LocalDate date = LocalDate.now();
       JSONArray tomorrowJSON = weatherAPIService.getWeatherData(date.toString().replace("-", ""));
       parseWeatherData.saveTomorrowData(tomorrowJSON);
   }


}
