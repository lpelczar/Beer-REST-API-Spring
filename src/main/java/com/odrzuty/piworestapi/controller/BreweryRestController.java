package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.model.removed.RemovedBrewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import com.odrzuty.piworestapi.repository.RemovedBreveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BreweryRestController {

    private final BreweryRepository breweryRepository;
    private final RemovedBreveryRepository removedBreveryRepository;

    @Autowired
    public BreweryRestController(BreweryRepository breweryRepository, RemovedBreveryRepository removedBreveryRepository) {

        this.breweryRepository = breweryRepository;
        this.removedBreveryRepository = removedBreveryRepository;
    }

    @GetMapping(value = "/breweries", produces = "application/json")
    public Collection<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    @PostMapping("/breweries")
    public Brewery createBrewery(@Valid @RequestBody Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    @GetMapping("/breweries/{id}")
    public Brewery getBreweryById(@PathVariable(value = "id") Integer breweryId) {
        return breweryRepository.findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));
    }

    @PutMapping("/breweries/{id}")
    public Brewery updateBrewery(@PathVariable(value = "id") Integer breweryId,
                           @Valid @RequestBody Brewery breweryFromJson) {

        Brewery brewery = breweryRepository.findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));

        breweryFromJson.setId(brewery.getId());

        return breweryRepository.save(brewery);
    }

    @DeleteMapping("/breweries/{id}")
    public ResponseEntity<?> deleteBrewery(@PathVariable(value = "id") Integer breweryId) {
        Brewery brewery = breweryRepository.findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));

        saveToRemoved(brewery);

        breweryRepository.delete(brewery);

        return ResponseEntity.ok().build();
    }

    private void saveToRemoved(Brewery brewery) {

        String breweryName = brewery.getName();
        String breweryAdress = brewery.getAddress1();
        String breweryCity = brewery.getCity();
        String breweryCode = brewery.getCode();
        String breweryState = brewery.getState();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String removed = LocalDateTime.now().format(dtf);
        removedBreveryRepository.save(new RemovedBrewery(breweryName, breweryAdress, breweryState, breweryCity, breweryCode, removed));

    }
}
