package com.odrzuty.piworestapi.repository;


import com.odrzuty.piworestapi.model.Style;

import java.util.List;

public interface StyleRepository {

    List<Style> getAll();
    int create(Style style);
    void update(Style style);
    Style getById(int id);
    void delete(Style style);
    void deleteAll();
}
