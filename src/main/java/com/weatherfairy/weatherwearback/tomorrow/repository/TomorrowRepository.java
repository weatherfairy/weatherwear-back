package com.weatherfairy.weatherwearback.tomorrow.repository;

import com.weatherfairy.weatherwearback.tomorrow.entity.Tomorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TomorrowRepository extends JpaRepository<Tomorrow, Long> {
}
