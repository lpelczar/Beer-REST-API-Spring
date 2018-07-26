package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Brewery;

public interface BreweryService {
    Iterable<Brewery> findAll();
    Brewery find(Integer id);
    void save(Brewery brewery);
    void remove(Integer id);
}
