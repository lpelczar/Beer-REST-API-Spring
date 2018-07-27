package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.model.Style;

import java.util.List;

public interface StyleService {
    List<Style> findAll();
    Style save(Style style);
    Style findById(int styleId);
    void delete(Style style);
}
