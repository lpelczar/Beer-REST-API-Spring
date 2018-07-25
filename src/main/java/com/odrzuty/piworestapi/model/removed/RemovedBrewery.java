package com.odrzuty.piworestapi.model.removed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "removed_breweries")
@Setter
@Getter
@ToString
public class RemovedBrewery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 255)
    String name;

    @Size(max = 255)
    String address1;

    @Size(max = 255)
    String city;

    @Size(max = 255)
    String state;

    @Size(max = 25)
    String code;

    String removed;

    public RemovedBrewery() {
    }

    public RemovedBrewery(@Size(max = 255) String name, @Size(max = 255) String address1, @Size(max = 255) String city, @Size(max = 255) String state, @Size(max = 25) String code, String removed) {
        this.name = name;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.code = code;
        this.removed = removed;
    }
}
