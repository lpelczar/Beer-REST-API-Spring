package com.odrzuty.piworestapi.controller.removed;

import com.odrzuty.piworestapi.model.removed.RemovedBeer;
import com.odrzuty.piworestapi.repository.removed.RemovedBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RemovedBeerController {

    private final RemovedBeerRepository removedBeerRepository;

    @Autowired
    public RemovedBeerController(RemovedBeerRepository removedBeerRepository) {
        this.removedBeerRepository = removedBeerRepository;
    }

    @GetMapping(value = "/removed-beers", produces = "application/json")
    public Collection<RemovedBeer> getAllCategories() {
        return removedBeerRepository.findAll();
    }
}
