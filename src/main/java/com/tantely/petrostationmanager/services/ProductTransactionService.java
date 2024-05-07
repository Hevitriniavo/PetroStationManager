package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.dtos.FuelTransactionInfo;

import java.util.List;

public interface ProductTransactionService {
    List<FuelTransactionInfo> findAllTransactionWithProductName();
}
