package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Brewery;

import java.util.List;

public interface BreweryService {
    List<Brewery> findAll();
}
