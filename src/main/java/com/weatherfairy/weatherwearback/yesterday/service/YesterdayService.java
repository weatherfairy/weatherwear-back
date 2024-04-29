package com.weatherfairy.weatherwearback.yesterday.service;

import com.weatherfairy.weatherwearback.yesterday.repository.YesterdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YesterdayService {

    private final YesterdayRepository yesterdayRepository;


}
