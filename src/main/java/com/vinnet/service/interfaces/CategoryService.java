package com.vinnet.service.interfaces;

import com.vinnet.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    Category save(Category category);
    void delete(Integer id);
}
