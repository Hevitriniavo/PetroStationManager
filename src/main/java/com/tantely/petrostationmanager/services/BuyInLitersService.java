package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.dtos.BuyInLiterRequest;

import java.util.Map;

public interface BuyInLitersService {
    Map<String, String> buyInLiters(BuyInLiterRequest request);
}
