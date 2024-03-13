package com.weatherfairy.weatherwearback.shortweather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherfairy.weatherwearback.shortweather.dto.ShortWeatherDto;
import com.weatherfairy.weatherwearback.shortweather.repository.ShortWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortWeatherService {
    private final ShortWeatherRepository shortWeatherRepository;

    private static final String API_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    private static final String API_KEY = "QzV66hYDsl4qVR0m7X4gUA1OH3oNwQg218380W16pj6IP%2BJ1qJ%2FegVAXv8O%2FOL0JqSN%2BY91Zt6Xp2eq4cHLBsw%3D%3D";

    public String getShortWeather() {
        String apiUrl = API_URL + "?serviceKey=" + API_KEY + "&numOfRows=1000&pageNo=1&dataType=json&base_date=20240313&base_time=0200&nx=60&ny=127";
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(apiUrl, String.class);
    }

    /*public List<ShortWeatherDto> getShortWeather() {
        String apiUrl = API_URL + "?serviceKey=" + API_KEY + "&numOfRows=1000&pageNo=1&dataType=json&base_date=20240313&base_time=0200&nx=60&ny=127";
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(apiUrl, String.class);

        return parseResponse(response);
    }

    private List<ShortWeatherDto> parseResponse(String response) {
        List<ShortWeatherDto> shortWeathers = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode responseNode = rootNode.path("response");
            JsonNode bodyNode = responseNode.path("body");
            JsonNode itemsNode = bodyNode.path("items");

            Iterator<JsonNode> elements = itemsNode.elements();
            while (elements.hasNext()) {
                JsonNode itemNode = elements.next();
                String category = itemNode.path("category").asText();

                if ("TMP".equals(category)) {
                    ShortWeatherDto shortWeatherDto = new ShortWeatherDto();
                    shortWeatherDto.setDate(itemNode.path("fcstDate").asInt());
                    shortWeatherDto.setTime(itemNode.path("fcstTime").asInt());
                    shortWeatherDto.setTemp((float) itemNode.path("fcstValue").asDouble());

                    shortWeathers.add(shortWeatherDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shortWeathers;
    }*/
}