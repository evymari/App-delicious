package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
@Entity
@AllArgsConstructor


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

    public Dessert(String name, String description, String countryOfOrigin, String ingredients, String image) {
        this.name = name;
        this.description = description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dessert dessert = (Dessert) o;
        return Objects.equals(id, dessert.id) &&
                Objects.equals(name, dessert.name) &&
                Objects.equals(description, dessert.description) &&
                Objects.equals(countryOfOrigin, dessert.countryOfOrigin) &&
                Objects.equals(ingredients, dessert.ingredients) &&
                Objects.equals(image, dessert.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, countryOfOrigin, ingredients, image);
    }

    @Override
    public String toString() {
        return "Dessert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



