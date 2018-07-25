package com.odrzuty.piworestapi.repository.removed;

import com.odrzuty.piworestapi.model.removed.RemovedBrewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemovedBreveryRepository extends JpaRepository<RemovedBrewery, Integer> {
}
