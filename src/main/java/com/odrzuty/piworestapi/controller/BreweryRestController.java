package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import com.odrzuty.piworestapi.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BreweryRestController {

    private final BreweryService breweryService;

    @Autowired
    public BreweryRestController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping(value = "/breweries", produces = "application/json")
    public Collection<Brewery> getAllBreweries() {
        return breweryService.findAll();
    }

    @PostMapping("/breweries")
    public Brewery createBrewery(@Valid @RequestBody Brewery brewery) {
        return breweryService.save(brewery);
    }

    @GetMapping("/breweries/{id}")
    public Brewery getBreweryById(@PathVariable(value = "id") Integer breweryId) {
        return breweryService.findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));
    }

    @PutMapping("/breweries/{id}")
    public Brewery updateBrewery(@PathVariable(value = "id") Integer breweryId,
                           @Valid @RequestBody Brewery breweryFromJson) {

        Brewery brewery = breweryService.findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));

        breweryFromJson.setId(brewery.getId());

        return breweryService.save(brewery);
    }

    @DeleteMapping("/breweries/{id}")
    public ResponseEntity<?> deleteBrewery(@PathVariable(value = "id") Integer breweryId) {
        Brewery brewery = breweryService.findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));
        breweryService.delete(brewery);
        return ResponseEntity.ok().build();
    }
}
