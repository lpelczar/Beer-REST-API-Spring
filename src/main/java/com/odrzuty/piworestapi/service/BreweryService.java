package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Brewery;

import java.util.Collection;
import java.util.List;

public interface BreweryService {
    Collection<Brewery> findAll();
    Brewery save(Brewery brewery);
    Brewery findById(int breweryId);
    void delete(Brewery brewery);
}
