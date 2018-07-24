package com.odrzuty.piworestapi.repository;


import com.odrzuty.piworestapi.model.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> getAll();

    List<Beer> getAll(int start, int limit);
    int create(Beer beer);
    Beer getById(int id);
    void update(Beer beer);
    void deleteAll();
    void deleteById(int id);
}
