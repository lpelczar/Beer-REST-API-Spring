package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.model.removed.RemovedBrewery;
import com.odrzuty.piworestapi.repository.removed.RemovedBreveryRepository;
import com.odrzuty.piworestapi.service.BreweryService;
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

    private final BreweryService breweryService;
    private final RemovedBreveryRepository removedBreveryRepository;

    @Autowired
    public BreweryRestController(BreweryService breweryService, RemovedBreveryRepository removedBreveryRepository) {
        this.breweryService = breweryService;
        this.removedBreveryRepository = removedBreveryRepository;
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
        return breweryService.findById(breweryId);
    }

    @PutMapping("/breweries/{id}")
    public Brewery updateBrewery(@PathVariable(value = "id") Integer breweryId,
                                 @Valid @RequestBody Brewery breweryFromJson) {
        Brewery brewery = breweryService.findById(breweryId);
        breweryFromJson.setId(brewery.getId());
        return breweryService.save(brewery);
    }

    @DeleteMapping("/breweries/{id}")
    public ResponseEntity<?> deleteBrewery(@PathVariable(value = "id") Integer breweryId) {
        Brewery brewery = breweryService.findById(breweryId);
        saveToRemoved(brewery);
        breweryService.delete(brewery);
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
