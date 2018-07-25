package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Brewery;

import java.util.List;
import java.util.Optional;

public interface BreweryService {
    List<Brewery> findAll();
    Brewery save(Brewery brewery);
    Brewery findById(int breweryId);
}
