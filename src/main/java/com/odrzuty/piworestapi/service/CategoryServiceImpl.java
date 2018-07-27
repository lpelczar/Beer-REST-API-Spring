package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Category;
import com.odrzuty.piworestapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final LoggerService loggerService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, LoggerService loggerService) {
        this.categoryRepository = categoryRepository;
        this.loggerService = loggerService;
    }

    @Override
    public List<Category> findAll() {
        loggerService.logInfo("(CATEGORIES) All entities were requested.");
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        loggerService.logInfo(
                String.format("(CATEGORIES) Category \"%s\" has been added to database",
                        category.getName())
        );
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(int categoryId) {
        Category requestedCategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Style", "id", categoryId));
        loggerService
                .logInfo(String.format(
                        "(CATEGORIES) Category \"%s\" has been requested from database",
                        requestedCategory.getName())
                );
        return requestedCategory;
    }

    @Override
    public void delete(Category category) {
        loggerService.logInfo(String.format(
                "(CATEGORIES) Category \"%s\" has been deleted from database",
                category.getName())
        );
        categoryRepository.delete(category);
    }
}
