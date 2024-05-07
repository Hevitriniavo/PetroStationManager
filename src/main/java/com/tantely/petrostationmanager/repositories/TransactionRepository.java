package com.tantely.petrostationmanager.repositories;

import com.tantely.petrostationmanager.entities.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findById(Integer id) throws SQLException;

    Transaction update(Transaction toUpdate) throws SQLException;

    Transaction create(Transaction toCreate) throws SQLException;

    Optional<Transaction> delete(Integer id) throws SQLException;

    List<Transaction> findAll() throws SQLException;
}
