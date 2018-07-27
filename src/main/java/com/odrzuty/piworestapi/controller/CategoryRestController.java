package com.odrzuty.piworestapi.controller;


import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.exception.ResourceRelatedException;
import com.odrzuty.piworestapi.model.Category;
import com.odrzuty.piworestapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryRestController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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

        try {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));


            categoryRepository.delete(category);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            //log ex
            throw new ResourceRelatedException("Category", "id", categoryId);
        }
    }


}
