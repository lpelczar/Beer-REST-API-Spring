package com.odrzuty.piworestapi.controller;


import com.odrzuty.piworestapi.exception.ResourceNotFoundException;
import com.odrzuty.piworestapi.model.Style;
import com.odrzuty.piworestapi.model.removed.RemovedStyle;
import com.odrzuty.piworestapi.repository.StyleRepository;
import com.odrzuty.piworestapi.repository.removed.RemovedStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class StyleRestController {

    private final StyleRepository styleRepository;
    private final RemovedStyleRepository removedStyleRepository;

    @Autowired
    public StyleRestController(StyleRepository styleRepository, RemovedStyleRepository removedStyleRepository ) {
        this.styleRepository = styleRepository;
        this.removedStyleRepository = removedStyleRepository;
    }

    @GetMapping(value = "/styles", produces = "application/json")
    public Collection<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    @PostMapping("/styles")
    public Style createStyle(@Valid @RequestBody Style style) {
        return styleRepository.save(style);
    }

    @GetMapping("/styles/{id}")
    public Style getStyleById(@PathVariable(value = "id") Integer styleId) {
        return styleRepository.findById(styleId)
                .orElseThrow(() -> new ResourceNotFoundException("Style", "id", styleId));
    }

    @PutMapping("/styles/{id}")
    public Style updateStyle(@PathVariable(value = "id") Integer styleId,
                                 @Valid @RequestBody Style styleFromJson) {

        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new ResourceNotFoundException("Style", "id", styleId));

        styleFromJson.setId(style.getId());

        return styleRepository.save(style);
    }

    @DeleteMapping("/styles/{id}")
    public ResponseEntity<?> deleteStyle(@PathVariable(value = "id") Integer styleId) {
        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new ResourceNotFoundException("Style", "id", styleId));

        savedRemoved(style);

        styleRepository.delete(style);

        return ResponseEntity.ok().build();
    }

    private void savedRemoved(Style style) {
        
        String styleName = style.getName();
        String categoryName = style.getCategory().getName();
        removedStyleRepository.save(new RemovedStyle(styleName, categoryName));
    }
}
