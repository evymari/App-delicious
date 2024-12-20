package com.f5.delicious.dto;

import com.f5.delicious.entity.Dessert;

public record ResponseDessertDto (Long id, String name, String description, String  countryOfOrigin, String ingredients) {
    public static ResponseDessertDto fromEntity(Dessert dessert) {
        return new ResponseDessertDto(
                dessert.getId(),
                dessert.getName(),
                dessert.getDescription(),
                dessert.getCountryOfOrigin(),
                dessert.getIngredients()


        );
    }
}
