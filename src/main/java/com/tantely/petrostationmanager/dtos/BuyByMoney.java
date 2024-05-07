package com.tantely.petrostationmanager.dtos;

public record BuyByMoney (
        Integer stationId,
        Integer productId,
        Double amount
) {}

