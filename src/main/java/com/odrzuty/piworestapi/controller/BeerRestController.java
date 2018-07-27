package com.odrzuty.piworestapi.controller;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Beer;
import com.odrzuty.piworestapi.repository.BeerRepository;
import com.odrzuty.piworestapi.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/api")
public class BeerRestController {

    private final BeerService beerService;

    @Autowired
    public BeerRestController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = "/beers", produces = "application/json")
    public Collection<Beer> getAllBreweries() {
        return beerService.findAllByRemovedIsFalse();
    }

    @PostMapping("/beers")
    public Beer createBeer(@Valid @RequestBody Beer beer) {
        return beerService.save(beer);
    }

    @GetMapping("/beers/{id}")
    public Beer getBeerById(@PathVariable(value = "id") Integer beerId) {
        Beer beer = beerService.findBeerByIdAndRemovedIsFalse(beerId);
        if(beer == null ){
            throw new ResourceNotFoundException("Beer", "id", beerId);
        }else{
            return beer;
        }

    }

    @PutMapping("/beers/{id}")
    public Beer updateBeer(@PathVariable(value = "id") Integer beerId,
                                 @Valid @RequestBody Beer beerFromJson) {

        Beer beer = beerService.findById(beerId);
        beerFromJson.setId(beer.getId());
        return beerService.save(beer);
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<?> deleteBeer(@PathVariable(value = "id") Integer beerId) {
        Beer beer = beerService.findById(beerId);
        beer.setRemoved(true);
        beerService.save(beer);
        return ResponseEntity.ok().build();
    }

}
