package dto;

import jakarta.validation.constraints.*;
import entity.Dessert;

public record RequestDessertDto(
        @NotBlank(message = "Name cannot be empty")
        @Size(max = 50, message = "Name must have less than 50 characters")
        String name,

        @NotBlank(message = "Description cannot be empty")
        @Size(max = 255, message = "Description must have less than 255 characters")
        String description,

        @NotBlank(message = "Country of origin cannot be empty")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country of origin must not contain numbers")
        String countryOfOrigin,

        @NotBlank(message = "Ingredients cannot be empty")
        String ingredients,

        @NotBlank(message = "Image URL cannot be empty")
        @Pattern(regexp = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]+(\\/[\\w\\-\\.]+)*\\.(jpg|gif|png)$",
                message = "Image must be a valid URL ending with .jpg, .gif, or .png")
        String image
) {
    public Dessert toEntity() {
        return new Dessert(this.name, this.description, this.countryOfOrigin, this.ingredients, this.image);
    }
}
