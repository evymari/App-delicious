package com.f5.delicious.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;



@Entity

public class Dessert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String countryOfOrigin;
    private String ingredients;
    private String image;  // URL de la imagen del postre
@ManyToOne
@JoinColumn(name = "idCreator",nullable = false)
    private Creator creator;
    public Dessert(String name, String description, String countryOfOrigin, String ingredients, String image,Creator creator) {
        this.name = name;
        this.description = description;
        this.creator = creator;

        // Validación para asegurar que el país de origen no esté vacío o en blanco
        if (countryOfOrigin == null || countryOfOrigin.isBlank()) {
            this.countryOfOrigin = "Unknown";
        } else {
            this.countryOfOrigin = countryOfOrigin;
        }

        this.ingredients = ingredients;

        // Validación para asegurar que la URL de la imagen no esté vacía o sea inválida
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }
        this.image = image;
    }
    public Dessert() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}



