package com.weatherfairy.weatherwearback.yesterday.repository;

import com.weatherfairy.weatherwearback.yesterday.entity.Yesterday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YesterdayRepository extends JpaRepository<Yesterday, Long> {
}
