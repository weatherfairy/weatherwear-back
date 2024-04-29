package com.weatherfairy.weatherwearback.clothes.service;

import com.weatherfairy.weatherwearback.clothes.entity.Clothes;
import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
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
    private final GetCurrentData getCurrentData;

    public List<Long> recommendClothesByCategories(String temp) {

        TempCategory tempCategory = getCurrentData.returnTempCategory(temp);

        List<Long> recommendedClothesIds = new ArrayList<>();

        recommendedClothesIds.add(getRandomClothes(ClothesCategory.TOP, tempCategory).getClothesId());
        recommendedClothesIds.add(getRandomClothes(ClothesCategory.BOTTOM, tempCategory).getClothesId());

        return recommendedClothesIds;
    }

    private Clothes getRandomClothes(ClothesCategory clothesCategory, TempCategory tempCategory) {
        List<Clothes> clothesList = clothesRepository.findAllByClothesCategoryAndTempCategory(clothesCategory, tempCategory);


        Random random = new Random();
        return clothesList.get(0);
    }
}

