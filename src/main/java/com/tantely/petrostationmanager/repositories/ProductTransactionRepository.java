package com.tantely.petrostationmanager.repositories;

import com.tantely.petrostationmanager.dtos.FuelTransactionInfo;

import java.sql.SQLException;
import java.util.List;

public interface ProductTransactionRepository {

    List<FuelTransactionInfo> findAllTransactionWithProductName() throws SQLException;
}
