package com.tantely.petrostationmanager.rest.controllers;


import com.tantely.petrostationmanager.dtos.UpdateStockRequest;
import com.tantely.petrostationmanager.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionRestController {
    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/update/stock")
    public Map<String, String> updateStock(@RequestBody UpdateStockRequest request) {
            return transactionService.updateStock(request);
    }
}
