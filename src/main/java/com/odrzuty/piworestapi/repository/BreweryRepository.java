package com.odrzuty.piworestapi.repository;

import com.odrzuty.piworestapi.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Integer> {

    Collection <Brewery> findAllByRemovedIsFalse();
    Brewery findByIdAndRemovedIsFalse(Integer id);
}
