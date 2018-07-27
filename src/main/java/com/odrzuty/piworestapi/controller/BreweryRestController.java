package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import com.odrzuty.piworestapi.service.BreweryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BreweryRestController {

    private BreweryService service;

    @Autowired
    public BreweryRestController(@Qualifier("breweryService") BreweryService service) {
        this.service = service;
    }

    @GetMapping(value = "/breweries", produces = "application/json")
    public Iterable<Brewery> getAllBreweries() {
        return service.findAll();
    }

    @PostMapping("/breweries")
    public Brewery createBrewery(@Valid @RequestBody Brewery brewery) {
        service.save(brewery);
        return brewery;
    }

    @GetMapping("/breweries/{id}")
    public Brewery getBreweryById(@PathVariable(value = "id") Integer breweryId) {
        return service.find(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));
    }

    @PutMapping("/breweries/{id}")
    public Brewery updateBrewery(@PathVariable(value = "id") Integer breweryId,
                           @Valid @RequestBody Brewery breweryFromJson) {

        Brewery brewery = service.find(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));

        breweryFromJson.setId(brewery.getId());
        service.save(brewery);
        return brewery;
    }

    @DeleteMapping("/breweries/{id}")
    public ResponseEntity<?> deleteBrewery(@PathVariable(value = "id") Integer breweryId) {
        Brewery brewery = service.find(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));

        service.delete(brewery);

        return ResponseEntity.ok().build();
    }
}
