package com.tantely.petrostationmanager.repositories.impl;

import com.tantely.petrostationmanager.entities.Transaction;
import com.tantely.petrostationmanager.enums.TransactionType;
import com.tantely.petrostationmanager.repositories.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final Connection connection;

    public TransactionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Transaction> findById(Integer id) throws SQLException {
        final var query = "SELECT * FROM transactions WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (final var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToTransaction(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Transaction update(Transaction toUpdate) throws SQLException {
        final var query = "UPDATE transactions SET station_id = ?, product_id = ?, type = ?, quantity = ?, amount = ?, date_transaction = ? WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            setTransactionParameters(stmt, toUpdate);
            stmt.setInt(7, toUpdate.getId());
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                return toUpdate;
            }
        }
        return null;
    }

    @Override
    public Transaction create(Transaction toCreate) throws SQLException {
        final String query = "INSERT INTO transactions (station_id, product_id, type, quantity, amount, date_transaction) VALUES (?, ?, ?, ?, ?, ?)";
        try (final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setTransactionParameters(stmt, toCreate);
            final int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (final var generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        final var id = generatedKeys.getInt(1);
                        return this.findById(id).orElse(null);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Transaction> delete(Integer id) throws SQLException {
        final var foundStation = this.findById(id);
        final var query = "DELETE FROM transactions WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                return foundStation;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAll() throws SQLException {
        final var query = "SELECT * FROM transactions";
        final var transactions = new ArrayList<Transaction>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()) {
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        }
        return transactions;
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        return new Transaction()
                .setId(rs.getInt("id"))
                .setStationId(rs.getInt("station_id"))
                .setProductId(rs.getInt("product_id"))
                .setType(TransactionType.valueOf(rs.getString("type")))
                .setQuantity(rs.getDouble("quantity"))
                .setAmount(rs.getDouble("amount"))
                .setDateTransaction(rs.getTimestamp("date_transaction").toLocalDateTime());
    }


    private void setTransactionParameters(PreparedStatement stmt, Transaction transaction) throws SQLException {
        stmt.setInt(1, transaction.getStationId());
        stmt.setInt(2, transaction.getProductId());
        stmt.setString(3, transaction.getType().name());
        stmt.setDouble(4, transaction.getQuantity());
        stmt.setDouble(5, transaction.getAmount());
        stmt.setTimestamp(6, Timestamp.valueOf(transaction.getDateTransaction()));
    }
}
