package com.tantely.petrostationmanager.repositories;

import com.tantely.petrostationmanager.entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Integer id) throws SQLException;

    Product update(Product toUpdate) throws SQLException;

    Product create(Product toCreate) throws SQLException;

    Optional<Product> delete(Integer id) throws SQLException;

    List<Product> findAll() throws SQLException;
}
