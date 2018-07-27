package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> findAll();
    Beer save(Beer beer);
    Beer findById(int beerId);
    void delete(Beer beer);
}
