package com.f5.delicious.dto;

import com.f5.delicious.entity.Creator;

public record ResponseCreatorDto(Long id, String name, String email, String phone, String address) {

    public static ResponseCreatorDto fromEntity(Creator creator) {
        Creator user;
        return new ResponseCreatorDto(
                creator.getId(),
                creator.getName(),
                creator.getEmail(),
                creator.getPhone(),
                creator.getAddress()
        );
    }
}