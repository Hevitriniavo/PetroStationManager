package com.tantely.petrostationmanager.rest.controllers;

import com.tantely.petrostationmanager.dtos.DailySalesSummary;
import com.tantely.petrostationmanager.services.DailySalesSummaryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class DailySalesSummaryRestController {
    private final DailySalesSummaryService dailySalesSummaryService;

    public DailySalesSummaryRestController(DailySalesSummaryService dailySalesSummaryService) {
        this.dailySalesSummaryService = dailySalesSummaryService;
    }

    @GetMapping("/daily/sales")
    public List<DailySalesSummary> getDailySalesSummary(){
        return dailySalesSummaryService.findDailySalesSummary();
    }
}
