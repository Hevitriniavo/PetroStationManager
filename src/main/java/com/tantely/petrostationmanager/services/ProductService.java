package com.tantely.petrostationmanager.services;

import com.tantely.petrostationmanager.entities.Product;

import java.util.List;

public interface ProductService {
    Product updateProduct(Integer id, Product product);

    Product createProduct(Product product);

    Product findProductById(Integer id);

    Product destroyProductById(Integer id);

    List<Product> findAll();
}
