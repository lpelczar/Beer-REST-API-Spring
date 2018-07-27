package com.odrzuty.piworestapi.controller;


import com.odrzuty.piworestapi.exception.ResourceRelatedException;
import com.odrzuty.piworestapi.model.Category;
import com.odrzuty.piworestapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories", produces = "application/json")
    public Collection<Category> getAllBreweries() {
        return categoryService.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Integer categoryId) {
        return categoryService.findById(categoryId);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") Integer categoryId,
                                 @Valid @RequestBody Category categoryFromJson) {
        Category category = categoryService.findById(categoryId);
        categoryFromJson.setId(category.getId());
        return categoryService.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Integer categoryId) {

        try {
            Category category = categoryService.findById(categoryId);
            categoryService.delete(category);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            //log ex
            throw new ResourceRelatedException("Category", "id", categoryId);
        }
    }
}
