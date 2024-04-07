package com.weatherfairy.weatherwearback.common.weatherdata;

import com.weatherfairy.weatherwearback.today.entity.Today;
import com.weatherfairy.weatherwearback.today.repository.TodayRepository;
import com.weatherfairy.weatherwearback.tomorrow.entity.Tomorrow;
import com.weatherfairy.weatherwearback.tomorrow.repository.TomorrowRepository;
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

    public void saveTodayWeather(JSONArray weatherDataJSON) {

        todayRepository.deleteAllInBatch();

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
                .locationName("서울특별시 성북구")
                .locationX(60)
                .locationY(127)
                .temperature(tmpValues)
                .skyCategory(skyValues)
                .rain(pcpValues)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .build();

        todayRepository.save(today);
    }

    public void saveTomorrowData(JSONArray weatherDataJSON) {

        tomorrowRepository.deleteAllInBatch();

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
                .locationName("서울특별시 성북구")
                .locationX(60)
                .locationY(127)
                .temperature(tmpValues)
                .skyCategory(skyValues)
                .rain(pcpValues)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .build();

        tomorrowRepository.save(tomorrow);
    }

}
