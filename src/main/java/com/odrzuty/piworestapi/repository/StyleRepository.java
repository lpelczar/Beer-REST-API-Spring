package com.odrzuty.piworestapi.repository;

import com.odrzuty.piworestapi.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer > {
}
