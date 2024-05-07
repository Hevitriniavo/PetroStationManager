package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.dtos.BuyByMoney;

import java.util.Map;

public interface BuyByMoneyService {
    Map<String, String> buyByMoney(BuyByMoney request);
}
