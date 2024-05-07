package com.tantely.petrostationmanager.entities;

import com.tantely.petrostationmanager.enums.FuelType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Product {
    Integer id;
    FuelType name;
    Double unitPrice;
    Double quantity;

    public double calculateQuantityByAmount(double amount){
        return  amount / unitPrice;
    }
    public double calculateAmountByQuantity(double quantity){
        return  quantity * unitPrice;
    }
}
