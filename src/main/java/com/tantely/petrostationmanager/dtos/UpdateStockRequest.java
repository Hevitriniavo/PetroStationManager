package com.tantely.petrostationmanager.dtos;


import java.util.List;

public record UpdateStockRequest (
        Integer stationId,
        List<ProductQuantityUpdate> productQuantityUpdates
){}
