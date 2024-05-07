package com.tantely.petrostationmanager.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tantely.petrostationmanager.enums.TransactionType;

import java.time.LocalDateTime;

public record FuelTransactionInfo (
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime transactionDateTime,
        String fuelType,
        TransactionType transactionType,
         Double quantity
) {}
