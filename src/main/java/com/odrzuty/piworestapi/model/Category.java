package com.odrzuty.piworestapi.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "categories")
@Setter
@Getter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(max = 250)
    @Column(name = "cat_name")
    String name;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean removed = false;

    public Category() {
    }

    public Category(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("beersJPA");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Category category = entityManager.find(Category.class, id);

        this.id = category.id;
        this.name = category.name;

        entityManager.getTransaction().commit();

    }
}
