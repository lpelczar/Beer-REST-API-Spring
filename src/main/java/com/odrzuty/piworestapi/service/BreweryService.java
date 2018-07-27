package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Brewery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BreweryService {
    Iterable<Brewery> findAll();
    Optional<Brewery> find(Integer id);
    void save(Brewery brewery);
    void delete(Brewery brewery);
}
