package com.tantely.petrostationmanager.dtos;

public record BuyInLiterRequest (
        Integer stationId,
        Integer productId,
        Double quantity
) {}
