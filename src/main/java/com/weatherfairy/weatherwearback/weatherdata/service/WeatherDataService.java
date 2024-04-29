package com.weatherfairy.weatherwearback.weatherdata.service;

import com.weatherfairy.weatherwearback.clothes.service.ClothesRecommendService;
import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
import com.weatherfairy.weatherwearback.weatherdata.dto.MainPageResponse;
import com.weatherfairy.weatherwearback.weatherdata.entity.DailyData;
import com.weatherfairy.weatherwearback.weatherdata.entity.WeeklyData;
import com.weatherfairy.weatherwearback.weatherdata.repository.DailyDataRepository;
import com.weatherfairy.weatherwearback.weatherdata.repository.WeeklyDataRepository;
import com.weatherfairy.weatherwearback.yesterday.entity.Yesterday;
import com.weatherfairy.weatherwearback.yesterday.repository.YesterdayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private final DailyDataRepository dailyDataRepository;
    private final YesterdayRepository yesterdayRepository;
    private final ClothesRecommendService clothesRecommendService;
    private final WeeklyDataRepository weeklyDataRepository;

    int currentHour = LocalTime.now().getHour();

    @Transactional
    public void getYesterdayFromToday() {

        yesterdayRepository.deleteAllInBatch();

        List<DailyData> todayData = dailyDataRepository.findAll();

        for (DailyData today : todayData) {
            Yesterday yesterday = Yesterday.builder()
                            .locationName(today.getLocationName())
                            .skyCategory(today.getSkyCategory())
                            .temperature(today.getTemperature())
                            .build();

            yesterdayRepository.save(yesterday);
        }

    }


    @Transactional(readOnly = true)
    public MainPageResponse getMainPageData(String locationName) {

        DailyData data = dailyDataRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        Yesterday yesterday = yesterdayRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        WeeklyData weeklyData = weeklyDataRepository.findByLocationName(locationName)
                .orElseThrow(() -> new EntityNotFoundException("해당 지역이 존재하지 않습니다."));

        List<String> temp = data.getTemperature();
        List<Integer> sky = data.getSkyCategory();

        List<String> yesterdayTemp = yesterday.getTemperature();
        List<Integer> yesterdaySky = yesterday.getSkyCategory();

        List<String> temp1 = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();
        List<String> temp3 = new ArrayList<>();
        List<String> temp4 = new ArrayList<>();
        List<Integer> sky1 = new ArrayList<>();
        List<Integer> sky2 = new ArrayList<>();
        List<Integer> sky3 = new ArrayList<>();
        List<Integer> sky4 = new ArrayList<>();

        String currentTemp = temp.get(currentHour);
        String rain = data.getRain().get(currentHour);
        String wind = data.getWindSpeed().get(currentHour);
//        Long top = clothesRecommendService.recommendClothesByCategories(currentTemp).get(0);
//        Long bottom = clothesRecommendService.recommendClothesByCategories(currentTemp).get(1);


        for (int i = 0 ; i < 24 ; i += 3) {
            temp1.add(yesterdayTemp.get(i));
            sky1.add(yesterdaySky.get(i));
            temp2.add(temp.get(i));
            sky2.add(sky.get(i));
        }
        for (int i = 24 ; i < 48 ; i +=3) {
            temp3.add(temp.get(i));
            sky3.add(sky.get(i));
        }
        for (int i = 48 ; i < 72 ; i += 3) {
            temp4.add(temp.get(i));
            sky4.add(sky.get(i));
        }

        List<String> weeklyRain = weeklyData.getDayRain();
        List<Integer> weeklySky = weeklyData.getDaySky();

        return new MainPageResponse(currentTemp, rain, wind, 19L,10L, temp1, sky1, temp2, sky2, temp3, sky3, temp4, sky4,
                weeklyRain, weeklyRain, weeklySky, weeklySky, weeklyData.getMinTemp(), weeklyData.getMaxTemp());

    }

}
