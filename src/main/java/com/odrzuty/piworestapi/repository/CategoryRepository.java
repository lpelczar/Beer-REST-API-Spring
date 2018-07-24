package com.odrzuty.piworestapi.repository;


import com.odrzuty.piworestapi.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getAll();
    int create(Category category);
    void update(Category category);
    Category getById(int id);
    void delete(Category category);
    void deleteAll();
}
