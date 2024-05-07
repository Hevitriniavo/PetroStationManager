package com.tantely.petrostationmanager.rest.controllers;

import com.tantely.petrostationmanager.dtos.BuyInLiterRequest;
import com.tantely.petrostationmanager.services.BuyInLitersService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class BuyInLitersRestController {
    private final BuyInLitersService buyInLitersService;

    public BuyInLitersRestController(BuyInLitersService buyInLitersService) {
        this.buyInLitersService = buyInLitersService;
    }

    @PostMapping("/buy/liters")
    public Map<String, String> buyInLiters(@RequestBody BuyInLiterRequest request) {
        return buyInLitersService.buyInLiters(request);
    }
}
