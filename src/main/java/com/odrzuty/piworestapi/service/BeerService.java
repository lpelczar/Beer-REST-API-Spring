package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Beer;

import java.util.Collection;

public interface BeerService {
    Beer save(Beer beer);
    Beer findById(int beerId);
    void delete(Beer beer);
    Collection<Beer> findAllByRemovedIsFalse();
    Beer findBeerByIdAndRemovedIsFalse(Integer id);
}
