package com.weatherfairy.weatherwearback.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.*;

@Converter(autoApply = true)
public class FloatListConverter implements AttributeConverter<List<Float>, String> {
    @Override
    public String convertToDatabaseColumn(List<Float> floats) {
        if (floats == null || floats.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Float floatValue : floats) {
            stringBuilder.append(floatValue).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public List<Float> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        dbData = dbData.substring(1, dbData.length() - 1);
        String[] parts = dbData.split(",");
        List<Float> floatList = new ArrayList<>();
        for (String part : parts) {
            try {
                floatList.add(Float.parseFloat(part.trim()));
            } catch (NumberFormatException e) {
                System.out.println("e = " + e);
            }
        }
        return floatList;
    }

}
