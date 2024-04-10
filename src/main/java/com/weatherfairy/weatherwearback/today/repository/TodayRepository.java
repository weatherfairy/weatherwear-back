package com.weatherfairy.weatherwearback.today.repository;

import com.weatherfairy.weatherwearback.today.entity.Today;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodayRepository extends JpaRepository<Today, Long> {

    Optional<Today> findByLocationName(String locationName);
}
