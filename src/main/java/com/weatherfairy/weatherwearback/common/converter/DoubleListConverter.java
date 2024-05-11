package com.weatherfairy.weatherwearback.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
public class DoubleListConverter implements AttributeConverter<List<Double>, String> {
    @Override
    public String convertToDatabaseColumn(List<Double> doubles) {
        if (doubles == null || doubles.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Double doubleValue : doubles) {
            stringBuilder.append(doubleValue).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public List<Double> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        dbData = dbData.substring(1, dbData.length() - 1);
        String[] parts = dbData.split(",");
        List<Double> doubleList = new ArrayList<>();
        for (String part : parts) {
            try {
                doubleList.add(Double.parseDouble(part.trim()));
            } catch (NumberFormatException e) {
                doubleList.add(0.0);
            }
        }
        return doubleList;
    }

}
