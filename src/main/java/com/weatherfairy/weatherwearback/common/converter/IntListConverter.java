package com.weatherfairy.weatherwearback.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
public class IntListConverter implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        if (integers == null || integers.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integerValue : integers) {
            stringBuilder.append(integerValue).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        dbData = dbData.substring(1, dbData.length() - 1);
        String[] parts = dbData.split(",");
        List<Integer> intList = new ArrayList<>();
        for (String part : parts) {
            try {
                intList.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
            }
        }
        return intList;
    }
}
