package com.odrzuty.piworestapi.repository;


import com.odrzuty.piworestapi.model.Brewery;

import java.util.List;

public interface BreweryRepository {

    List<Brewery> getAll();
    int create(Brewery brewery);
    void update(Brewery brewery);
    Brewery getById(int id);
    void delete(Brewery brewery);
    void deleteAll();
}
