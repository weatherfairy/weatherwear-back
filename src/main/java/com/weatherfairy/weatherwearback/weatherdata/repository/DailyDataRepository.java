package com.weatherfairy.weatherwearback.weatherdata.repository;

import com.weatherfairy.weatherwearback.weatherdata.entity.DailyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyDataRepository extends JpaRepository<DailyData, Long> {

    Optional<DailyData> findByLocationName(String locationName);
}
