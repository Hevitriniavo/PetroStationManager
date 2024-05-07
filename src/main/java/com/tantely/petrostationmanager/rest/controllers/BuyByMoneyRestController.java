package com.tantely.petrostationmanager.rest.controllers;

import com.tantely.petrostationmanager.dtos.BuyByMoney;
import com.tantely.petrostationmanager.services.BuyByMoneyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class BuyByMoneyRestController {
    private final BuyByMoneyService buyByMoneyService;

    public BuyByMoneyRestController(BuyByMoneyService buyByMoneyService) {
        this.buyByMoneyService = buyByMoneyService;
    }

    @PostMapping("/buy/money")
    public Map<String, String> buyByMoney(@RequestBody BuyByMoney request) {
        return buyByMoneyService.buyByMoney(request);
    }
}
