package com.tantely.petrostationmanager.dtos;

import java.time.LocalDate;

public record DailySalesSummary (
         LocalDate date,
        Double fuelAddedEssence,
        Double fuelAddedGasoline,
        Double fuelAddedDiesel,
        Double fuelSoldEssence,
        Double fuelSoldGasoline,
        Double fuelSoldDiesel,
        Double remainingQuantityEssence,
        Double remainingQuantityGasoline,
        Double remainingQuantityDiesel
){}
