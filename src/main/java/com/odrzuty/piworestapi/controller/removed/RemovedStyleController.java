package com.odrzuty.piworestapi.controller.removed;

import com.odrzuty.piworestapi.model.removed.RemovedStyle;
import com.odrzuty.piworestapi.repository.removed.RemovedStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RemovedStyleController {

    private final RemovedStyleRepository removedStyleRepository;

    @Autowired
    public RemovedStyleController(RemovedStyleRepository removedStyleRepository) {
        this.removedStyleRepository = removedStyleRepository;
    }

    @GetMapping(value = "/removed-styles", produces = "application/json")
    public Collection<RemovedStyle> getAllCategories() {
        return removedStyleRepository.findAll();
    }
}
