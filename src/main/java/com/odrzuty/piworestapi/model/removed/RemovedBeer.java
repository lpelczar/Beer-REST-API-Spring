package com.odrzuty.piworestapi.model.removed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "removed-beers")
@Setter
@Getter
public class RemovedBeer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brewery_name")
    private String breweryName;

    @Column(name = "cat_name")
    private String categoryName;

    @Column(name = "style_name")
    private String styleName;

    public RemovedBeer() {
    }

    public RemovedBeer(String breweryName, String categoryName, String styleName) {
        this.breweryName = breweryName;
        this.categoryName = categoryName;
        this.styleName = styleName;
    }
}
