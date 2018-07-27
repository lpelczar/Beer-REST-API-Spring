package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category findById(int categoryId);
    void delete(Category category);
}
