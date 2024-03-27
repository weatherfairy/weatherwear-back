package com.weatherfairy.weatherwearback.clothes.service;

import com.weatherfairy.weatherwearback.clothes.entity.Clothes;
import com.weatherfairy.weatherwearback.common.enums.ClothesCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.clothes.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ClothesRecommendService {

    private final ClothesRepository clothesRepository;

    public List<Long> recommendClothes(float temp) {
        TempCategory tempCategory = getTempCategory(temp);
        return recommendClothesByCategories(tempCategory);
    }

    private TempCategory getTempCategory(float temp) {
        return Arrays.stream(TempCategory.values())
                .filter(category -> isTempInRange(temp, category))
                .findFirst()
                .orElse(null);
    }

    private boolean isTempInRange(float temp, TempCategory category) {
        String[] range = category.getTempRange().split(" ~ ");
        if (range.length == 1) {
            float singleTemp = Float.parseFloat(range[0].replace("°C", ""));
            return temp == singleTemp;
        } else {
            float minTemp = Float.parseFloat(range[0].replace("°C", ""));
            float maxTemp = Float.parseFloat(range[1].replace("°C", ""));
            return temp >= minTemp && temp <= maxTemp;
        }
    }

    private List<Long> recommendClothesByCategories(TempCategory tempCategory) {
        List<Long> recommendedClothesIds = new ArrayList<>();

        recommendedClothesIds.add(getRandomClothes(ClothesCategory.TOP, tempCategory).getClothesId());
        recommendedClothesIds.add(getRandomClothes(ClothesCategory.BOTTOM, tempCategory).getClothesId());

        return recommendedClothesIds;
    }

    private Clothes getRandomClothes(ClothesCategory clothesCategory, TempCategory tempCategory) {
        List<Clothes> clothesList = clothesRepository.findAllByClothesCategoryAndTempCategory(clothesCategory, tempCategory);
        Random random = new Random();
        return clothesList.get(random.nextInt(clothesList.size()));
    }
}

