package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Beer;
import com.odrzuty.piworestapi.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final LoggerService loggerService;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository, LoggerService loggerService) {
        this.beerRepository = beerRepository;
        this.loggerService = loggerService;
    }

    @Override
    public List<Beer> findAll() {
        loggerService.logInfo("(BEERS) All entities were requested.");
        return beerRepository.findAll();
    }

    @Override
    public Beer save(Beer beer) {
        loggerService.logInfo(
                String.format("(BEERS) Beer \"%s\" has been added to database",
                        beer.getName())
        );
        return beerRepository.save(beer);
    }

    @Override
    public Beer findById(int beerId) {
        Beer requestedBeer = beerRepository
                .findById(beerId)
                .orElseThrow(() -> new ResourceNotFoundException("Beer", "id", beerId));
        loggerService
                .logInfo(String.format(
                        "(BEERS) Beer \"%s\" has been requested from database",
                        requestedBeer.getName())
                );
        return requestedBeer;
    }

    @Override
    public void delete(Beer beer) {
        loggerService.logInfo(String.format(
                "(BEERS) Beer \"%s\" has been deleted from database",
                beer.getName())
        );
        beerRepository.delete(beer);
    }
}
