package com.weatherfairy.weatherwearback.common.weatherdata;

import com.weatherfairy.weatherwearback.common.locations.Locations;
import com.weatherfairy.weatherwearback.common.locations.LocationsRepository;
import com.weatherfairy.weatherwearback.today.repository.TodayRepository;
import com.weatherfairy.weatherwearback.tomorrow.repository.TomorrowRepository;
import com.weatherfairy.weatherwearback.yesterday.repository.YesterdayRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final WeatherAPIService weatherAPIService;
    private final ParseWeatherData parseWeatherData;
    private final LocationsRepository locationsRepository;

    private final YesterdayRepository yesterdayRepository;
    private final TodayRepository todayRepository;
    private final TomorrowRepository tomorrowRepository;

    @Scheduled(cron = "0 40 16 * * *")
    public void saveYesterdayData() throws ParseException, IOException {
        yesterdayRepository.deleteAllInBatch();

        LocalDate date = LocalDate.now().minusDays(2);
        processWeatherData(date, 3);
    }

    @Scheduled(cron = "0 0 23 * * *")
    public void saveTodayData() throws ParseException, IOException {
        todayRepository.deleteAllInBatch();

        LocalDate date = LocalDate.now().minusDays(1);
        processWeatherData(date, 2);
    }

    @Scheduled(cron = "0 30 23 * * *")
    public void saveTomorrowData() throws ParseException, IOException {
        tomorrowRepository.deleteAllInBatch();

        LocalDate date = LocalDate.now();
        processWeatherData(date, 1);
    }

    private void processWeatherData(LocalDate date, int dataType) throws ParseException, IOException {
        List<Locations> locations = locationsRepository.findAll();
        locations.forEach(location -> {
            try {
                JSONArray weatherData = weatherAPIService.getWeatherData(
                        date.toString().replace("-", ""),
                        location.getLocationX(),
                        location.getLocationY()
                );
                parseWeatherData.saveWeatherData(location.getLocationName(),weatherData, dataType);
            } catch (ParseException | IOException e) {
                e.printStackTrace(); // 에러 처리 로직 추가 필요
            }
        });
    }
}

