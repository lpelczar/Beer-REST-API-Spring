package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Style;
import com.odrzuty.piworestapi.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;
    private final LoggerService loggerService;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository, LoggerService loggerService) {
        this.styleRepository = styleRepository;
        this.loggerService = loggerService;
    }

    @Override
    public List<Style> findAll() {
        loggerService.logInfo("(STYLES) All entities were requested.");
        return styleRepository.findAll();
    }

    @Override
    public Style save(Style style) {
        loggerService.logInfo(
                String.format("(BREWERIES) Brewery \"%s\" has been added to database",
                        style.getName())
        );
        return styleRepository.save(style);
    }

    @Override
    public Style findById(int styleId) {
        Style requestedStyle = styleRepository
                .findById(styleId)
                .orElseThrow(() -> new ResourceNotFoundException("Style", "id", styleId));
        loggerService
                .logInfo(String.format(
                        "(BREWERIES) Brewery \"%s\" has been requested from database",
                        requestedStyle.getName())
                );
        return requestedStyle;
    }

    @Override
    public void delete(Style style) {
        loggerService.logInfo(String.format(
                "(STYLES) Style \"%s\" has been deleted from database",
                style.getName())
        );
        styleRepository.delete(style);
    }
}
