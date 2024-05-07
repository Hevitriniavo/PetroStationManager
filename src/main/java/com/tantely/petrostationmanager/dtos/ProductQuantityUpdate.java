package com.tantely.petrostationmanager.dtos;

public record ProductQuantityUpdate (
   Integer productId,
   Double quantity,
   Double amount,
   Double buyingPrice
) {}
