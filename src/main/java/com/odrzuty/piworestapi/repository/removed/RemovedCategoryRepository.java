package com.odrzuty.piworestapi.repository.removed;

import com.odrzuty.piworestapi.model.removed.RemovedCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemovedCategoryRepository extends JpaRepository<RemovedCategory, Integer> {
}
