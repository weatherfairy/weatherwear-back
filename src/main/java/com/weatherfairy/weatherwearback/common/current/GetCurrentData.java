package com.weatherfairy.weatherwearback.common.current;

import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.today.entity.Today;
import com.weatherfairy.weatherwearback.today.repository.TodayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class GetCurrentData {

    private final TodayRepository todayRepository;

    //0 ~ 23
    int currentHour = LocalTime.now().getHour();

    public float returnCurrentTemperature(String locationName) {

        Today today = todayRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        return today.getTemperature().get(currentHour);
    }

    public int returnCurrentSkyCategory(String locationName) {

        Today today = todayRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        return today.getSkyCategory().get(currentHour);
    }

    public TempCategory returnCurrentTempCategory(String locationName) {

        float currentTemp = returnCurrentTemperature(locationName);

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
