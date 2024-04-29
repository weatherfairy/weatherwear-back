package com.weatherfairy.weatherwearback.weatherdata.service;

import com.weatherfairy.weatherwearback.weatherdata.entity.DailyData;
import com.weatherfairy.weatherwearback.weatherdata.entity.WeeklyData;
import com.weatherfairy.weatherwearback.weatherdata.repository.DailyDataRepository;
import com.weatherfairy.weatherwearback.weatherdata.repository.WeeklyDataRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParseWeatherData {

    private final DailyDataRepository dailyDataRepository;
    private final WeeklyDataRepository weeklyDataRepository;

    public DailyData saveDailyData(String locationName, JSONArray list) {

        List<String> tmpValues = new ArrayList<>();
        List<String> windValues = new ArrayList<>();
        List<String> popValues = new ArrayList<>();
        List<Integer> skyValues = new ArrayList<>();
        int index = 0;

        for (Object obj : list) {

            JSONObject item = (JSONObject) obj;
            JSONObject main = (JSONObject) item.get("main");
            JSONArray weather = (JSONArray) item.get("weather");
            JSONObject weatherObject = (JSONObject) weather.get(0);
            String weatherID = weatherObject.get("id").toString();

            tmpValues.add( main.get("temp").toString());
            skyValues.add(Integer.parseInt(weatherID.substring(0,1)));

            if (index < 24) {
                JSONObject wind = (JSONObject) item.get("wind");
                windValues.add(wind.get("speed").toString());
                popValues.add(item.get("pop").toString());
            }
            index++;
        }

        DailyData today = DailyData.builder()
                .locationName(locationName)
                .temperature(tmpValues)
                .skyCategory(skyValues)
                .rain(popValues)
                .windSpeed(windValues)
                .build();

        return dailyDataRepository.save(today);

    }

    public WeeklyData saveWeeklyData(String locationName, JSONArray list) {

        List<String> minTmpValues = new ArrayList<>();
        List<String> maxTmpValues = new ArrayList<>();
        List<String> popValues = new ArrayList<>();
        List<Integer> skyValues = new ArrayList<>();

        System.out.println("list.toJSONString() = " + list.toJSONString());


        for (Object obj : list) {
            JSONObject item = (JSONObject) obj;
            JSONObject temp = (JSONObject) item.get("temp");
            JSONArray weather = (JSONArray) item.get("weather");
            JSONObject weatherObject = (JSONObject) weather.get(0);
            String weatherID = weatherObject.get("id").toString();

            minTmpValues.add( temp.get("min").toString());
            maxTmpValues.add( temp.get("max").toString());
            skyValues.add(Integer.parseInt(weatherID.substring(0,1)));
            popValues.add(item.get("pop").toString());
        }

        WeeklyData weeklyData = WeeklyData.builder()
                .locationName(locationName)
                .maxTemp(maxTmpValues)
                .minTemp(minTmpValues)
                .dayRain(popValues)
                .nightRain(popValues)
                .daySky(skyValues)
                .nightSky(skyValues)
                .build();

        return weeklyDataRepository.save(weeklyData);

    }

}