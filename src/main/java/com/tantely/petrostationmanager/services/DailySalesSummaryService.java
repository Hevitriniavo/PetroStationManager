package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.dtos.DailySalesSummary;

import java.util.List;

public interface DailySalesSummaryService {
    List<DailySalesSummary> findDailySalesSummary();
}
