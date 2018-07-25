package com.odrzuty.piworestapi.controller.removed;

import com.odrzuty.piworestapi.model.removed.RemovedBrewery;
import com.odrzuty.piworestapi.repository.removed.RemovedBreveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RemovedBreweryController {

    private final RemovedBreveryRepository removedBreveryRepository;

    @Autowired
    public RemovedBreweryController(RemovedBreveryRepository removedBreveryRepository) { ;
        this.removedBreveryRepository = removedBreveryRepository;
    }

    @GetMapping(value = "/removed-breweries", produces = "application/json")
    public Collection<RemovedBrewery> getAllBreweries() {
        return removedBreveryRepository.findAll();
    }
}
