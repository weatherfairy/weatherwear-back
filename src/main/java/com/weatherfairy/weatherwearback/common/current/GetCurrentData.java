package com.weatherfairy.weatherwearback.common.current;

import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.weatherdata.entity.DailyData;
import com.weatherfairy.weatherwearback.weatherdata.repository.DailyDataRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCurrentData {

    private final DailyDataRepository todayRepository;

    //0 ~ 23
    int currentHour = LocalTime.now().getHour();

    public String returnCurrentTemperature(String locationName) {

        DailyData today = todayRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        return today.getTemperature().get(currentHour);
    }

    public int returnCurrentSkyCategory(String locationName) {

        DailyData today = todayRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        return today.getSkyCategory().get(currentHour);
    }

    public List<String> returnCurrentRainAndWind(String locationName) {

        DailyData today = todayRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        List<String> rainAndWind = new ArrayList<>();
        rainAndWind.add(today.getRain().get(currentHour));
        rainAndWind.add(today.getWindSpeed().get(currentHour));

        return rainAndWind;
    }

    public TempCategory returnCurrentTempCategory(String locationName) {

        Double currentTemp = Double.parseDouble(returnCurrentTemperature(locationName));

        if (currentTemp <= 4) {
            return TempCategory.WINTER1;
        } else if (currentTemp <= 8) {
            return TempCategory.WINTER2;
        } else if (currentTemp <= 11) {
            return TempCategory.AUTUMN1;
        } else if (currentTemp <= 16) {
            return TempCategory.AUTUMN2;
        } else if (currentTemp <= 19) {
            return TempCategory.SPRING1;
        } else if (currentTemp <= 22) {
            return TempCategory.SPRING2;
        } else if (currentTemp <= 27) {
            return TempCategory.SUMMER1;
        } else {
            return TempCategory.SUMMER2;
        }

    }

    public TempCategory returnTempCategory(String temp) {

        Double currentTemp = Double.parseDouble(temp);

        if (currentTemp <= 4) {
            return TempCategory.WINTER1;
        } else if (currentTemp <= 8) {
            return TempCategory.WINTER2;
        } else if (currentTemp <= 11) {
            return TempCategory.SPRING1;
        } else if (currentTemp <= 16) {
            return TempCategory.SPRING2;
        } else if (currentTemp <= 19) {
            return TempCategory.AUTUMN1;
        } else if (currentTemp <= 22) {
            return TempCategory.AUTUMN2;
        } else if (currentTemp <= 27) {
            return TempCategory.SUMMER1;
        } else {
            return TempCategory.SUMMER2;
        }

    }

    public TempCategory returnTempCategory(float currentTemp) {

        if (currentTemp <= 4) {
            return TempCategory.WINTER1;
        } else if (currentTemp <= 8) {
            return TempCategory.WINTER2;
        } else if (currentTemp <= 11) {
            return TempCategory.SPRING1;
        } else if (currentTemp <= 16) {
            return TempCategory.SPRING2;
        } else if (currentTemp <= 19) {
            return TempCategory.AUTUMN1;
        } else if (currentTemp <= 22) {
            return TempCategory.AUTUMN2;
        } else if (currentTemp <= 27) {
            return TempCategory.SUMMER1;
        } else {
            return TempCategory.SUMMER2;
        }

    }

}
