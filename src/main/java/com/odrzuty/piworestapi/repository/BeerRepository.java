package com.odrzuty.piworestapi.repository;

import com.odrzuty.piworestapi.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

    Collection<Beer> findAllByRemovedIsFalse();
    Beer findBeerByIdAndRemovedIsFalse(Integer id);
}
