package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Beer;
import com.odrzuty.piworestapi.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/api")
public class BeerRestController {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerRestController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @GetMapping(value = "/beers", produces = "application/json")
    public Collection<Beer> getAllBreweries() {
        return beerRepository.findAllByRemovedIsFalse();
    }

    @PostMapping("/beers")
    public Beer createBeer(@Valid @RequestBody Beer beer) {
        return beerRepository.save(beer);
    }

    @GetMapping("/beers/{id}")
    public Beer getBeerById(@PathVariable(value = "id") Integer beerId) {
        Beer beer = beerRepository.findBeerByIdAndRemovedIsFalse(beerId);
        if(beer == null ||beer.isRemoved()){
            throw new ResourceNotFoundException("Beer", "id", beerId);
        }else{
            return beer;
        }

    }

    @PutMapping("/beers/{id}")
    public Beer updateBeer(@PathVariable(value = "id") Integer beerId,
                                 @Valid @RequestBody Beer beerFromJson) {

        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new ResourceNotFoundException("Beer", "id", beerId));

        beerFromJson.setId(beer.getId());

        return beerRepository.save(beer);
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<?> deleteBeer(@PathVariable(value = "id") Integer beerId) {
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new ResourceNotFoundException("Beer", "id", beerId));

        beer.setRemoved(true);
        beerRepository.save(beer);


        return ResponseEntity.ok().build();
    }

}
