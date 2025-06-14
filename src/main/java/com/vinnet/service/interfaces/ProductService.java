package com.vinnet.service.interfaces;

import com.vinnet.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    List<Product> findByTitleContainingIgnoreCase(String title);
    Product save(Product product);
    void delete(Integer id);
}
