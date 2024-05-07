package com.tantely.petrostationmanager.repositories.impl;

import com.tantely.petrostationmanager.entities.Product;
import com.tantely.petrostationmanager.enums.FuelType;
import com.tantely.petrostationmanager.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Connection connection;

    public ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Product> findById(Integer id) throws SQLException {
        final var query = "SELECT * FROM products WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToProduct(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Product update(Product toUpdate) throws SQLException {
        final var query = "UPDATE products SET name = ?, unit_price = ?, quantity = ? WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            setProductParameters(stmt, toUpdate);
            stmt.setInt(4, toUpdate.getId());
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                return toUpdate;
            }
        }
        return null;
    }

    @Override
    public Product create(Product toCreate) throws SQLException {
        final var query = "INSERT INTO products (name, unit_price, quantity) VALUES (?, ?, ?)";
        try (final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setProductParameters(stmt, toCreate);
            final var rows = stmt.executeUpdate();
            if (rows > 0) {
                try (final var generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        final var id = generatedKeys.getInt(1);
                        return findById(id).orElse(null);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Product> delete(Integer id) throws SQLException {
        final var foundProduct = this.findById(id);
        final var query = "DELETE FROM products WHERE id = ?";
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            var rows = stmt.executeUpdate();
            if (rows > 0) {
                return foundProduct;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        final var query = "SELECT * FROM products";
        final var products = new ArrayList<Product>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        }
        return products;
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product()
                .setId(rs.getInt("id"))
                .setName(FuelType.valueOf(rs.getString("name")))
                .setQuantity(rs.getDouble("quantity"))
                .setUnitPrice(rs.getDouble("unit_price"));
    }

    private void setProductParameters(PreparedStatement stmt, Product product) throws SQLException {
        stmt.setString(1, product.getName().name());
        stmt.setDouble(2, product.getUnitPrice());
        stmt.setDouble(3, product.getQuantity());
    }
}
