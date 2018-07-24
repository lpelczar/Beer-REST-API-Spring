package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BreweryRestController {

    private final BreweryRepository breweryRepository;

    @Autowired
    public BreweryRestController(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
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

        breweryRepository.delete(brewery);

        return ResponseEntity.ok().build();
    }
}
