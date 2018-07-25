package com.odrzuty.piworestapi.controller.removed;

import com.odrzuty.piworestapi.model.removed.RemovedCategory;
import com.odrzuty.piworestapi.repository.removed.RemovedCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RemovedCategoryController {

    private final RemovedCategoryRepository removedCategoryRepository;

    @Autowired
    public RemovedCategoryController(RemovedCategoryRepository removedCategoryRepository) {
        this.removedCategoryRepository = removedCategoryRepository;
    }

    @GetMapping(value = "/removed-categories", produces = "application/json")
    public Collection<RemovedCategory> getAllCategories() {
        return removedCategoryRepository.findAll();
    }
}
