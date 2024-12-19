package com.f5.delicious.dto;

import com.f5.delicious.entity.Creator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestCreatorDto(
        @NotBlank(message = "Name can not be empty")
        @Size(min = 3, max = 20, message = "Name must contain 3 to 20 letters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
        String name,

        @Email(message = "Email format is not correct")
        String email,


        @NotBlank(message = "Phone can not be empty")
        @Size(min = 9, max = 9, message = "Phone must have 9 digits")
        @Pattern(regexp = "\\d{9}", message = "Phone must have 9 digits, mustn't have letters")
        String phone,
        @NotBlank(message = "Address can not be empty")
        String address
) {

    public Creator toEntity() {
        return new Creator(
                this.name,
                this.email,
                this.phone,
                this.address
        );
    }
}
