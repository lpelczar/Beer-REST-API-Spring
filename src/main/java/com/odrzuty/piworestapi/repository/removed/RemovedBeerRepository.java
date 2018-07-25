package com.odrzuty.piworestapi.repository.removed;

import com.odrzuty.piworestapi.model.removed.RemovedBeer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemovedBeerRepository extends JpaRepository<RemovedBeer, Integer> {
}
