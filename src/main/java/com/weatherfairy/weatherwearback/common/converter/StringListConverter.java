package com.weatherfairy.weatherwearback.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String stringValue : strings) {
            stringBuilder.append(stringValue).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        dbData = dbData.substring(1, dbData.length() - 1);
        String[] parts = dbData.split(",");
        List<String> stringList = new ArrayList<>();
        for (String part : parts) {
            try {
                stringList.add(part.trim());
            } catch (NumberFormatException e) {
                System.out.println("e = " + e);
            }
        }
        return stringList;
    }

}
