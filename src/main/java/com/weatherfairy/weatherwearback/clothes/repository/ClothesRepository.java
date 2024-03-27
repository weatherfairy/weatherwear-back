package com.weatherfairy.weatherwearback.clothes.repository;

import com.weatherfairy.weatherwearback.clothes.entity.Clothes;
import com.weatherfairy.weatherwearback.common.enums.ClothesCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    List<Clothes> findAllByClothesCategoryAndTempCategory(ClothesCategory clothesCategory, TempCategory tempCategory);
}
