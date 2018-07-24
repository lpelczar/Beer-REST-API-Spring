package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
