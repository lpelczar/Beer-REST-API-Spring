package com.odrzuty.piworestapi.controller;


import com.odrzuty.piworestapi.exception.ResourceRelatedException;
import com.odrzuty.piworestapi.model.Style;
import com.odrzuty.piworestapi.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class StyleRestController {

    private final StyleService styleService;

    @Autowired
    public StyleRestController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping(value = "/styles", produces = "application/json")
    public Collection<Style> getAllStyles() {
        return styleService.findAll();
    }

    @PostMapping("/styles")
    public Style createStyle(@Valid @RequestBody Style style) {
        return styleService.save(style);
    }

    @GetMapping("/styles/{id}")
    public Style getStyleById(@PathVariable(value = "id") Integer styleId) {
        return styleService.findById(styleId);
    }

    @PutMapping("/styles/{id}")
    public Style updateStyle(@PathVariable(value = "id") Integer styleId,
                                 @Valid @RequestBody Style styleFromJson) {
        Style style = styleService.findById(styleId);
        styleFromJson.setId(style.getId());
        return styleService.save(style);
    }

    @DeleteMapping("/styles/{id}")
    public ResponseEntity<?> deleteStyle(@PathVariable(value = "id") Integer styleId) {

        try {
            Style style = styleService.findById(styleId);
            styleService.delete(style);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            //log ex
            throw new ResourceRelatedException("Style", "id", styleId);
        }
    }
}
