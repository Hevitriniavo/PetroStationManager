package com.tantely.petrostationmanager.repositories.impl;

import com.tantely.petrostationmanager.dtos.DailySalesSummary;
import com.tantely.petrostationmanager.repositories.DailySalesSummaryRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DailySalesSummaryRepositoryImpl implements DailySalesSummaryRepository {
    private final Connection connection;

    public DailySalesSummaryRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DailySalesSummary> findDailySalesSummary() throws SQLException {
        final var query = """
                        SELECT t.date_transaction,
                           SUM(CASE WHEN p.name = 'DIESEL' AND t.type = 'ENTRY' THEN t.quantity ELSE 0 END) AS quantity_added_essence,
                           SUM(CASE WHEN p.name = 'GASOLINE' AND t.type = 'ENTRY'  THEN t.quantity ELSE 0 END) AS quantity_added_gasoline,
                           SUM(CASE WHEN p.name = 'PETROL' AND t.type = 'ENTRY' THEN t.quantity ELSE 0 END) AS quantity_added_petrol,
                           SUM(CASE WHEN p.name = 'DIESEL' AND t.type = 'SORTIE' THEN t.quantity ELSE 0 END) AS quantity_sold_essence,
                           SUM(CASE WHEN p.name = 'GASOLINE' AND t.type = 'SORTIE' THEN t.quantity ELSE 0 END) AS quantity_sold_gasoline,
                           SUM(CASE WHEN p.name = 'PETROL' AND t.type = 'SORTIE' THEN t.quantity ELSE 0 END) AS quantity_sold_petrol,
                           SUM(CASE WHEN p.name = 'DIESEL' THEN p.quantity ELSE 0 END) AS remaining_quantity_essence,
                           SUM(CASE WHEN p.name = 'GASOLINE' THEN p.quantity ELSE 0 END) AS remaining_quantity_gasoline,
                           SUM(CASE WHEN p.name = 'PETROL' THEN p.quantity ELSE 0 END) AS remaining_quantity_petrol
                        FROM products AS p
                             INNER JOIN transactions AS t ON p.id = t.product_id
                        GROUP BY t.date_transaction;
              """;
        final var dailySalesSummaries = new ArrayList<DailySalesSummary>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                dailySalesSummaries.add(mapResultSetToDailySalesSummary(rs));
            }
        }
        return dailySalesSummaries;
    }

    private DailySalesSummary mapResultSetToDailySalesSummary(ResultSet rs) throws SQLException {
        return new DailySalesSummary(
                rs.getTimestamp("date_transaction").toLocalDateTime().toLocalDate(),
                rs.getDouble("quantity_added_essence"),
                rs.getDouble("quantity_added_gasoline"),
                rs.getDouble("quantity_added_petrol"),
                rs.getDouble("quantity_sold_essence"),
                rs.getDouble("quantity_sold_gasoline"),
                rs.getDouble("quantity_sold_petrol"),
                rs.getDouble("remaining_quantity_essence"),
                rs.getDouble("remaining_quantity_gasoline"),
                rs.getDouble("remaining_quantity_petrol")
        );
    }
}
