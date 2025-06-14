package com.vinnet.dao;

import com.vinnet.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    List<Product> findByTitleContainingIgnoreCase(String keyword);
}
