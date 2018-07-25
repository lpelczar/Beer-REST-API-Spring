package com.odrzuty.piworestapi.controller;


import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Category;
import com.odrzuty.piworestapi.model.removed.RemovedCategory;
import com.odrzuty.piworestapi.repository.CategoryRepository;
import com.odrzuty.piworestapi.repository.removed.RemovedCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final CategoryRepository categoryRepository;
    private final RemovedCategoryRepository removedCategoryRepository;

    @Autowired
    public CategoryRestController(CategoryRepository categoryRepository, RemovedCategoryRepository removedCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.removedCategoryRepository = removedCategoryRepository;
    }

    @GetMapping(value = "/categories", produces = "application/json")
    public Collection<Category> getAllBreweries() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") Integer categoryId,
                                 @Valid @RequestBody Category categoryFromJson) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryFromJson.setId(category.getId());

        return categoryRepository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        saveRemoved(category);

        categoryRepository.delete(category);

        return ResponseEntity.ok().build();
    }

    private void saveRemoved(Category category) {

        String categoryName = category.getName();
        removedCategoryRepository.save(new RemovedCategory(categoryName));

    }
}
