package com.odrzuty.piworestapi.service;


import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BreweryServiceImpl implements BreweryService {

    private BreweryRepository repository;
    private LoggerService logger;

    public BreweryServiceImpl(BreweryRepository repository, LoggerService loggerService) {
        this.repository = repository;
        this.logger = loggerService;
    }
    @Override
    public Iterable<Brewery> findAll() {
        logger.logInfo("Breweries have been loaded by client");
        return repository.findAll();
    }

    @Override
    public Optional<Brewery> find(Integer id) {
        return null;
    }

    @Override
    public void save(Brewery brewery) {

    }

    @Override
    public void delete(Brewery brewery) {

    }
}
