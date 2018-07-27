package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Brewery;
import com.odrzuty.piworestapi.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService {

    private final BreweryRepository breweryRepository;
    private final LoggerService loggerService;

    @Autowired
    public BreweryServiceImpl(BreweryRepository breweryRepository, LoggerService loggerService) {
        this.breweryRepository = breweryRepository;
        this.loggerService = loggerService;
    }

    @Override
    public List<Brewery> findAll() {
        loggerService.logInfo("(BREWERIES) All entities were requested.");
        return breweryRepository.findAll();
    }

    @Override
    public Brewery save(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    @Override
    public Brewery findById(int breweryId) {
        return breweryRepository
                .findById(breweryId)
                .orElseThrow(() -> new ResourceNotFoundException("Brewery", "id", breweryId));
    }

    @Override
    public void delete(Brewery brewery) {
        breweryRepository.delete(brewery);
    }
}
