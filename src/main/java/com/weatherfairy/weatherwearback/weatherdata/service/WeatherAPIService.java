package com.weatherfairy.weatherwearback.weatherdata.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class WeatherAPIService {

    @Value("${weather.api-key}")
    private String serviceKey;
    private final String url_daily = "https://pro.openweathermap.org/data/2.5/forecast/hourly";
    private final String url_weekly = "https://pro.openweathermap.org/data/2.5/forecast/daily";

    public JSONArray getDailyWeather(String latitude, String longitude) throws ParseException, IOException{

        StringBuilder urlBuilder = new StringBuilder(url_daily);
        urlBuilder.append("?" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=" + URLEncoder.encode("metric", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("cnt", "UTF-8") + "=" + URLEncoder.encode("73", "UTF-8"));

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String sb = "";
        String line;
        while ((line = rd.readLine()) != null) {
            sb += line;
        }

        rd.close();
        conn.disconnect();


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb);

        JSONArray item = (JSONArray) jsonObject.get("list");

        return item;

    }


    public JSONArray getWeeklyWeather(String latitude, String longitude) throws ParseException, IOException{

        StringBuilder urlBuilder = new StringBuilder(url_weekly);
        urlBuilder.append("?" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=" + URLEncoder.encode("metric", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("cnt", "UTF-8") + "=" + URLEncoder.encode("8", "UTF-8"));

        URL url = new URL(urlBuilder.toString());

        System.out.println("url = " + url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String sb = "";
        String line;
        while ((line = rd.readLine()) != null) {
            sb += line;
        }

        rd.close();
        conn.disconnect();


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb);

        JSONArray item = (JSONArray) jsonObject.get("list");

        return item;

    }
}
