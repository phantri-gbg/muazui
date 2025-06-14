package com.vinnet.service.impl;

import com.vinnet.dao.ProductDAO;
import com.vinnet.model.Product;
import com.vinnet.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> findByTitleContainingIgnoreCase(String title) {
        return productDAO.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public void delete(Integer id) {
        productDAO.deleteById(id);
    }
}
