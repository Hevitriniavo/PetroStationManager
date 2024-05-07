package com.tantely.petrostationmanager.entities;


import com.tantely.petrostationmanager.enums.TransactionType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Transaction {
    Integer id;
    TransactionType type;
    Double quantity;
    Double amount;
    LocalDateTime dateTransaction;
    Integer stationId;
    Integer productId;
}
