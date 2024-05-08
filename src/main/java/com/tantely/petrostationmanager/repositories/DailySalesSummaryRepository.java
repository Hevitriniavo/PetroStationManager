package com.tantely.petrostationmanager.repositories;

import com.tantely.petrostationmanager.dtos.DailySalesSummary;

import java.sql.SQLException;
import java.util.List;

public interface DailySalesSummaryRepository {
    List<DailySalesSummary> findDailySalesSummary() throws SQLException;
}
