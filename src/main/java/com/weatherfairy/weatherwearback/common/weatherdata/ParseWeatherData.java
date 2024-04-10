package com.weatherfairy.weatherwearback.common.weatherdata;

import com.weatherfairy.weatherwearback.today.entity.Today;
import com.weatherfairy.weatherwearback.today.repository.TodayRepository;
import com.weatherfairy.weatherwearback.tomorrow.entity.Tomorrow;
import com.weatherfairy.weatherwearback.tomorrow.repository.TomorrowRepository;
import com.weatherfairy.weatherwearback.yesterday.entity.Yesterday;
import com.weatherfairy.weatherwearback.yesterday.repository.YesterdayRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParseWeatherData {

        private final TodayRepository todayRepository;
        private final TomorrowRepository tomorrowRepository;
        private final YesterdayRepository yesterdayRepository;

        public void saveWeatherData(String locationName, JSONArray weatherDataJSON, int dataType) {
            switch (dataType) {
                case 1:
                    saveTodayWeather(locationName, weatherDataJSON);
                    break;
                case 2:
                    saveTomorrowData(locationName, weatherDataJSON);
                    break;
                case 3:
                    saveYesterdayWeather(locationName, weatherDataJSON);
                    break;
                default:
                    break;
            }
        }

    private void saveTodayWeather(String locationName, JSONArray weatherDataJSON) {

//        todayRepository.deleteAllInBatch();

        List<Float> tmpValues = new ArrayList<>();
        List<String> pcpValues = new ArrayList<>();
        List<Integer> skyValues = new ArrayList<>();
        float maxTemp = 0;
        float minTemp = 0;

        for (Object obj : weatherDataJSON) {
            JSONObject dataJson = (JSONObject) obj;
            String category = (String) dataJson.get("category");
            String fcstValue = (String) dataJson.get("fcstValue");

            if (category.equals("TMP")) {
                tmpValues.add(Float.valueOf(fcstValue));
            } else if (category.equals("PCP")) {
                pcpValues.add(fcstValue);
            } else if (category.equals("SKY")) {
                skyValues.add(Integer.parseInt(fcstValue));
            } else if (category.equals("TMX")) {
                maxTemp = Float.parseFloat(fcstValue);
            } else if (category.equals("TMN")) {
                minTemp = Float.parseFloat(fcstValue);
            }
        }

        Today today = Today.builder()
                .locationName(locationName)
                .temperature(tmpValues)
                .skyCategory(skyValues)
                .rain(pcpValues)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .build();

        todayRepository.save(today);
    }

    private void saveTomorrowData(String locationName, JSONArray weatherDataJSON) {

//        tomorrowRepository.deleteAllInBatch();

        List<Float> tmpValues = new ArrayList<>();
        List<String> pcpValues = new ArrayList<>();
        List<Integer> skyValues = new ArrayList<>();
        float maxTemp = 0;
        float minTemp = 0;

        for (Object obj : weatherDataJSON) {
            JSONObject dataJson = (JSONObject) obj;
            String category = (String) dataJson.get("category");
            String fcstValue = (String) dataJson.get("fcstValue");

            if (category.equals("TMP")) {
                tmpValues.add(Float.valueOf(fcstValue));
            } else if (category.equals("PCP")) {
                pcpValues.add(fcstValue);
            } else if (category.equals("SKY")) {
                skyValues.add(Integer.parseInt(fcstValue));
            } else if (category.equals("TMX")) {
                maxTemp = Float.parseFloat(fcstValue);
            } else if (category.equals("TMN")) {
                minTemp = Float.parseFloat(fcstValue);
            }
        }

        Tomorrow tomorrow = Tomorrow.builder()
                .locationName(locationName)
                .temperature(tmpValues)
                .skyCategory(skyValues)
                .rain(pcpValues)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .build();

        tomorrowRepository.save(tomorrow);
    }

    private void saveYesterdayWeather(String locationName, JSONArray yesterdayJson) {
//        yesterdayRepository.deleteAllInBatch();

        List<Float> tmpValues = new ArrayList<>();
        List<String> pcpValues = new ArrayList<>();
        List<Integer> skyValues = new ArrayList<>();
        float maxTemp = 0;
        float minTemp = 0;

        for (Object obj : yesterdayJson) {
            JSONObject dataJson = (JSONObject) obj;
            String category = (String) dataJson.get("category");
            String fcstValue = (String) dataJson.get("fcstValue");

            if (category.equals("TMP")) {
                tmpValues.add(Float.valueOf(fcstValue));
            } else if (category.equals("PCP")) {
                pcpValues.add(fcstValue);
            } else if (category.equals("SKY")) {
                skyValues.add(Integer.parseInt(fcstValue));
            } else if (category.equals("TMX")) {
                maxTemp = Float.parseFloat(fcstValue);
            } else if (category.equals("TMN")) {
                minTemp = Float.parseFloat(fcstValue);
            }
        }

        Yesterday yesterday = Yesterday.builder()
                .locationName(locationName)
                .temperature(tmpValues)
                .skyCategory(skyValues)
                .rain(pcpValues)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .build();

        yesterdayRepository.save(yesterday);

    }
}