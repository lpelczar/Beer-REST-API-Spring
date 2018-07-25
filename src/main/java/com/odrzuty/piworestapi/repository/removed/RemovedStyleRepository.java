package com.odrzuty.piworestapi.repository.removed;

import com.odrzuty.piworestapi.model.removed.RemovedStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemovedStyleRepository extends JpaRepository<RemovedStyle, Integer> {
}
