package com.weatherfairy.weatherwearback.clothes.service;

import com.weatherfairy.weatherwearback.clothes.entity.Clothes;
import com.weatherfairy.weatherwearback.common.current.GetCurrentData;
import com.weatherfairy.weatherwearback.common.enums.ClothesCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.clothes.repository.ClothesRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    public List<Integer> recommendClothesByCategories(String temp) {

        TempCategory tempCategory = getCurrentData.returnTempCategory(temp);

        List<Integer> recommendedClothesNos = new ArrayList<>();

        recommendedClothesNos.add(getRandomClothes(ClothesCategory.TOP, tempCategory).getClothesNo());
        recommendedClothesNos.add(getRandomClothes(ClothesCategory.BOTTOM, tempCategory).getClothesNo());

        return recommendedClothesNos;
    }

    private Clothes getRandomClothes(ClothesCategory clothesCategory, TempCategory tempCategory) {
        List<Clothes> clothesList = clothesRepository.findAllByClothesCategoryAndTempCategory(clothesCategory, tempCategory);

        Random random = new Random();
        int index = random.nextInt(clothesList.size());

        return clothesList.get(index);
    }
}

