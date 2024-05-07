package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.dtos.UpdateStockRequest;

import java.util.Map;

public interface TransactionService {
    Map<String , String> updateStock(UpdateStockRequest toCreate);;
}
