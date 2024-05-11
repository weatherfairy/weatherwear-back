package com.weatherfairy.weatherwearback.weatherdata.repository;

import com.weatherfairy.weatherwearback.weatherdata.entity.WeeklyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeeklyDataRepository extends JpaRepository<WeeklyData, Long> {

    Optional<WeeklyData> findByLocationName(String locationName);
}
