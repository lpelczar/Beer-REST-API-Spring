package com.odrzuty.piworestapi.model.removed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "removed_categories")
@Setter
@Getter
@ToString

public class RemovedCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(max = 250)
    @Column(name = "cat_name")
    String name;

    public RemovedCategory() {
    }

    public RemovedCategory(@Size(max = 250) String name) {
        this.name = name;
    }
}
