package com.odrzuty.piworestapi.service;


import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;

public class BreweryServiceImpl implements BreweryService {

    private BreweryRepository repository;
    private LoggerService logger;

    public BreweryServiceImpl(BreweryRepository repository, LoggerService logger) {
        this.repository = repository;
        this.logger = logger;
    }
    @Override
    public Iterable<Brewery> findAll() {
        return null;
    }

    @Override
    public Brewery find(Integer id) {
        return null;
    }

    @Override
    public void save(Brewery brewery) {

    }

    @Override
    public void remove(Integer id) {

    }
}
