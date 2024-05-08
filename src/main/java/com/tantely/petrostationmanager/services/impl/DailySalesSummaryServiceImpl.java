package com.tantely.petrostationmanager.services.impl;

import com.tantely.petrostationmanager.dtos.DailySalesSummary;
import com.tantely.petrostationmanager.exceptions.InternalServerException;
import com.tantely.petrostationmanager.repositories.DailySalesSummaryRepository;
import com.tantely.petrostationmanager.services.DailySalesSummaryService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DailySalesSummaryServiceImpl implements DailySalesSummaryService {
    private final DailySalesSummaryRepository dailySalesSummaryRepository;

    public DailySalesSummaryServiceImpl(DailySalesSummaryRepository dailySalesSummaryRepository) {
        this.dailySalesSummaryRepository = dailySalesSummaryRepository;
    }

    @Override
    public List<DailySalesSummary> findDailySalesSummary() {
            try {
                return dailySalesSummaryRepository.findDailySalesSummary();
            } catch (SQLException e) {
                throw new InternalServerException(e.getMessage());
            }
    }
}
